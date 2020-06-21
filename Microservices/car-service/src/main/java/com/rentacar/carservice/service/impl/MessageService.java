package com.rentacar.carservice.service.impl;

import com.netflix.discovery.converters.Auto;
import com.rentacar.carservice.client.AuthClient;
import com.rentacar.carservice.dto.feignClient.AgentDTO;
import com.rentacar.carservice.dto.feignClient.SimpleUserDTO;
import com.rentacar.carservice.dto.feignClient.UserDTO;
import com.rentacar.carservice.dto.feignClient.UserMessageDTO;
import com.rentacar.carservice.dto.request.SeenRequest;
import com.rentacar.carservice.dto.response.AdMessageResponse;
import com.rentacar.carservice.dto.response.CarAccessoryResponse;
import com.rentacar.carservice.dto.response.MessageResponse;
import com.rentacar.carservice.dto.response.UserMessageResponse;
import com.rentacar.carservice.entity.Ad;
import com.rentacar.carservice.entity.CarAccessories;
import com.rentacar.carservice.entity.Message;
import com.rentacar.carservice.entity.MessageCarAccessories;
import com.rentacar.carservice.repository.IAdRepository;
import com.rentacar.carservice.repository.ICarAccessoriesRepository;
import com.rentacar.carservice.repository.IMessageCarAccessoriesRepository;
import com.rentacar.carservice.repository.IMessageRepository;
import com.rentacar.carservice.dto.request.SendMessageRequest;
import com.rentacar.carservice.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sun.management.Agent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MessageService implements IMessageService {

    private final IMessageRepository _messageRepository;

    public MessageService(IMessageRepository messageRepository) {
        _messageRepository = messageRepository;
    }

    @Autowired
    private AuthClient _authClient;

    @Autowired
    private IAdRepository _adRepository;

    @Autowired
    private ICarAccessoriesRepository _carAccessoriesRepository;

    @Autowired
    private IMessageCarAccessoriesRepository _messageCarAccessoriesRepository;

    @Override
    public List<MessageResponse> getAllReceivedMessagesForUser(UUID id) {
        SimpleUserDTO simpleUser = _authClient.getSimpleUser(id);
        UserMessageDTO userDTO = new UserMessageDTO();
        if(simpleUser.getUser() != null){
            userDTO.setId(simpleUser.getUser().getId());
            userDTO.setUserRole(simpleUser.getUser().getUserRole());
            userDTO.setName(simpleUser.getFirstName());
            userDTO.setLastName(simpleUser.getLastName());
        }else {
            userDTO = _authClient.getUser(id);
        }
        return mapMessagesToResponseDTO(userDTO);
    }

    @Override
    public ResponseEntity<String> sendMessage(SendMessageRequest request) {

        Message newMessage = new Message();
        newMessage.setText(request.getText());

        Ad ad = _adRepository.findOneById(request.getAd());
        newMessage.setAd(ad);

        newMessage.setUserSender(request.getSender());

        SimpleUserDTO simpleUserReceiver = _authClient.getSimpleUser(request.getReceiver());
        System.out.println(simpleUserReceiver);
        if(simpleUserReceiver.getUser() != null){
            newMessage.setUserReceiver(simpleUserReceiver.getUser().getId());
        }else {
            UserMessageDTO userDTO = _authClient.getUserFromAgent(request.getReceiver());
            newMessage.setUserReceiver(userDTO.getId());
        }


        Message msg = _messageRepository.save(newMessage);
        for(UUID id : request.getAccessories()){
            MessageCarAccessories mca = new MessageCarAccessories();
            mca.setMessage(msg);
            CarAccessories carAccess = _carAccessoriesRepository.findOneById(id);
            mca.setCar_accessory(carAccess);
            _messageCarAccessoriesRepository.save(mca);
        }
        return new ResponseEntity<>("Message sent", HttpStatus.CREATED);
    }

    @Override
    public List<MessageResponse> getAllSentMessagesFromUser(UUID user) {
        return null;
    }

    @Override
    public void seen(SeenRequest request, UUID id) {
        Message msg = _messageRepository.findOneById(id);
        msg.setSeen(request.isSeen());
        _messageRepository.save(msg);
    }

    @Override
    public List<MessageResponse> getMessagesBetweenUsers(UUID receiver, UUID sender) {
        System.out.println(receiver);
        System.out.println(sender);
        SimpleUserDTO simpleUserSender = _authClient.getSimpleUser(sender);
        UUID senderID;
        if(simpleUserSender.getUser() != null){
            senderID = simpleUserSender.getUser().getId();
        }else {
            UserMessageDTO userDTO = _authClient.getUserFromAgent(sender);
            senderID = userDTO.getId();
        }

        List<Message> allMessages = _messageRepository.findAll();
        allMessages = allMessages.stream().filter(message -> ((message.getUserSender().equals(senderID) && message.getUserReceiver().equals(receiver)) || (message.getUserReceiver().equals(senderID) && message.getUserSender().equals(receiver))))
                .collect(Collectors.toList());

        return mapMessagesBetweenTwoUsersToDTO(allMessages);
    }

    private List<MessageResponse> mapMessagesBetweenTwoUsersToDTO(List<Message> allMessages) {
        List<MessageResponse> retVal = new ArrayList<>();
        for(Message message : allMessages){
            MessageResponse dto = new MessageResponse();
            dto.setId(message.getId());
            dto.setText(message.getText());
            dto.setDateSent(message.getDateSent().toString());
            dto.setTimeSent(message.getTimeSent().toString());

            AdMessageResponse adDTO = new AdMessageResponse();
            adDTO.setShortCarDescription(makeShortCarDescription(message));
            adDTO.setAdID(message.getAd().getId());
            adDTO.setDateIssued(message.getAd().getCreationDate().toString());
            dto.setAd(adDTO);

            SimpleUserDTO simpleUserSender = _authClient.getSimpleUser(message.getUserSender());
            UserMessageResponse userDTO = new UserMessageResponse();
            if(simpleUserSender.getUser() != null){
                userDTO.setId(simpleUserSender.getId());
                userDTO.setName(simpleUserSender.getFirstName() + " " + simpleUserSender.getLastName());
            }else {
                UserMessageDTO uDTO = _authClient.getUser(message.getUserSender());
                userDTO.setId(uDTO.getSubjectID());
                userDTO.setName(uDTO.getName());
            }

            dto.setUser(userDTO);

            List<MessageCarAccessories> list = _messageCarAccessoriesRepository.findAll();
            List<CarAccessoryResponse> accessories = new ArrayList<>();
            for(MessageCarAccessories item : list){
                if(item.getMessage().getId().equals(message.getId())){
                    if(!item.isReviewed()){
                        CarAccessoryResponse carAccessoryResponse = new CarAccessoryResponse(
                                item.getCar_accessory().getId(),
                                item.getId(),
                                item.getCar_accessory().getDescription());
                        accessories.add(carAccessoryResponse);
                    }
                }
            }
            dto.setCarAccessories(accessories);
            dto.setSeen(message.isSeen());
            retVal.add(dto);
        }

        return retVal;
    }

    private List<MessageResponse> mapMessagesToResponseDTO(UserMessageDTO user) {
        List<MessageResponse> retVal = new ArrayList<>();
        List<Message> messages = _messageRepository.findAllByUserReceiver(user.getId());

        for(Message message : messages){
            MessageResponse msg = new MessageResponse();
            msg.setId(message.getId());
            msg.setText(message.getText());
            msg.setDateSent(message.getDateSent().toString());
            msg.setTimeSent(message.getTimeSent().toString());
            AdMessageResponse ad = new AdMessageResponse(message.getAd().getCreationDate().toString()
                    , makeShortCarDescription(message)
                    , message.getAd().getId());
            msg.setAd(ad);

            SimpleUserDTO simpleUserSender = _authClient.getSimpleUser(message.getUserSender());
            UserMessageResponse userDTO = new UserMessageResponse();
            if(simpleUserSender.getUser() != null){
                userDTO.setId(simpleUserSender.getId());
                userDTO.setName(simpleUserSender.getFirstName() + " " + simpleUserSender.getLastName());
            }else {
                UserMessageDTO uDTO = _authClient.getUser(message.getUserSender());
                userDTO.setId(uDTO.getSubjectID());
                userDTO.setName(uDTO.getName());
            }

            msg.setUser(userDTO);

            List<MessageCarAccessories> list = _messageCarAccessoriesRepository.findAll();
            List<CarAccessoryResponse> accessories = new ArrayList<>();
            for(MessageCarAccessories item : list){
                if(item.getMessage().getId().equals(message.getId())){
                    if(!item.isReviewed()){
                        CarAccessoryResponse carAccessoryResponse = new CarAccessoryResponse(
                                item.getCar_accessory().getId(),
                                item.getId(),
                                item.getCar_accessory().getDescription());
                        accessories.add(carAccessoryResponse);
                    }
                }
            }
            msg.setCarAccessories(accessories);
            msg.setSeen(message.isSeen());
            retVal.add(msg);
        }

        return retVal;
    }

    public String makeShortCarDescription(Message message){
        String retVal = "";

        retVal += message.getAd().getCar().getCarModel().getCarBrand().getName();
        retVal += " ";
        retVal += message.getAd().getCar().getCarModel().getName();
        retVal += ", ";
        retVal += message.getAd().getCar().getCarModel().getCarClass().getName();

        return retVal;
    }
}
