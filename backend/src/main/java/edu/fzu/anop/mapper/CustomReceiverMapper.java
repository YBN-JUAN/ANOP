package edu.fzu.anop.mapper;

import edu.fzu.anop.resource.ReceiverResource;

import java.util.List;

public interface CustomReceiverMapper {
    List<ReceiverResource> listReceiver(Integer notificationId, Integer groupId);
}
