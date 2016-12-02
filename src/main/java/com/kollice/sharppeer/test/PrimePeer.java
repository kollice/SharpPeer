package com.kollice.sharppeer.test;

import net.jxta.discovery.DiscoveryService;
import net.jxta.document.AdvertisementFactory;
import net.jxta.document.Document;
import net.jxta.document.MimeMediaType;
import net.jxta.exception.PeerGroupException;
import net.jxta.id.IDFactory;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;
import net.jxta.pipe.InputPipe;
import net.jxta.pipe.PipeID;
import net.jxta.pipe.PipeService;
import net.jxta.platform.ModuleClassID;
import net.jxta.platform.ModuleSpecID;
import net.jxta.protocol.ModuleClassAdvertisement;
import net.jxta.protocol.ModuleSpecAdvertisement;
import net.jxta.protocol.PipeAdvertisement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by 00259 on 2016/12/2.
 */
public class PrimePeer {
    private static PeerGroup peerGroup;
    private static DiscoveryService discoveryService;
    private static PipeService pipeService;
    private InputPipe inputPipe;
    private static final String PIPE_ADV_FILE = "primeserver_pipe.adv";

    public static void main(String[] args) {
        PrimePeer primePeer = new PrimePeer();
        primePeer.startJxta();
        primePeer.doAdvertise();
        primePeer.startService();

    }

    public PrimePeer() {}
    private void startJxta() {
        try {
            peerGroup = PeerGroupFactory.newNetPeerGroup();
            discoveryService = peerGroup.getDiscoveryService();
            pipeService = peerGroup.getPipeService();
        } catch (PeerGroupException e) {
            e.printStackTrace();
        }
    }
    private void doAdvertise() {
        ModuleClassAdvertisement moduleClassAdvertisement = (ModuleClassAdvertisement) AdvertisementFactory.newAdvertisement(ModuleClassAdvertisement.getAdvertisementType());
        ModuleClassID moduleClassID = IDFactory.newModuleClassID();
        moduleClassAdvertisement.setModuleClassID(moduleClassID);
        moduleClassAdvertisement.setName(ServiceConstants.CLASS_NAME);
        moduleClassAdvertisement.setDescription("A prime number crunching service.");

        try {
            discoveryService.publish(moduleClassAdvertisement);
            discoveryService.remotePublish(moduleClassAdvertisement,DiscoveryService.ADV);
            System.out.println("Published module class adv.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ModuleSpecAdvertisement moduleSpecAdvertisement = (ModuleSpecAdvertisement) AdvertisementFactory.newAdvertisement(ModuleSpecAdvertisement.getAdvertisementType());
        ModuleSpecID moduleSpecID = IDFactory.newModuleSpecID(moduleClassID);
        moduleSpecAdvertisement.setModuleSpecID(moduleSpecID);
        moduleSpecAdvertisement.setName(ServiceConstants.SPEC_NAME);
        moduleSpecAdvertisement.setDescription("Specification for a prime number crunching service");
        moduleSpecAdvertisement.setCreator("kollice");
        moduleSpecAdvertisement.setSpecURI("http://www.baijianye.com");
        moduleSpecAdvertisement.setVersion("Version 1.0");

        PipeAdvertisement pipeAdvertisement = null;
        try {
            FileInputStream fileInputStream = new FileInputStream(PIPE_ADV_FILE);
            pipeAdvertisement = (PipeAdvertisement)AdvertisementFactory.newAdvertisement(new MimeMediaType("text/xml"),fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            pipeAdvertisement = (PipeAdvertisement)AdvertisementFactory.newAdvertisement(PipeAdvertisement.getAdvertisementType());
            PipeID pipeID = IDFactory.newPipeID(peerGroup.getPeerGroupID());
            pipeAdvertisement.setPipeID(pipeID);
            Document document = pipeAdvertisement.getDocument(new MimeMediaType("text/xml"));
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(PIPE_ADV_FILE);
                document.sendToStream(fileOutputStream);
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException ex) {
                System.out.println("Can't save pipe advertisement to file " + PIPE_ADV_FILE);
                System.exit(-1);
            }
        }

        moduleSpecAdvertisement.setPipeAdvertisement(pipeAdvertisement);

        try {
            discoveryService.publish(moduleSpecAdvertisement);
            discoveryService.remotePublish(moduleSpecAdvertisement,DiscoveryService.ADV);
            System.out.println("Published module spec adv");
        } catch (IOException e) {
            System.out.println("Trouble publishing module spec adv: " + e.getMessage());
        }

        try {
            inputPipe = pipeService.createInputPipe(pipeAdvertisement);
            System.out.println("Created input pipe");
        } catch (IOException e) {
            System.out.println("Can't create input pipe. " + e.getMessage());
        }
    }
    private void startService() {

    }

    private void processInput(String high,String low) {

    }
}
