package edu.fzu.anop.service;

import edu.fzu.anop.resource.ReceiverAddResource;
import edu.fzu.anop.resource.ReceiverResource;

import java.util.List;

public interface ReceiverService {
    int addReceiver(ReceiverAddResource resource);

    List<ReceiverResource> listReceiver(int notificationId, int groupId);
}
