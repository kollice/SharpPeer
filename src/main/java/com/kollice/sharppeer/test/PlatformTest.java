package com.kollice.sharppeer.test;


import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;

/**
 * Created by 00259 on 2016/12/2.
 */
public class PlatformTest {
    public static void main(String[] args) {
        PeerGroup peerGroup = null;

        try {
            peerGroup = PeerGroupFactory.newNetPeerGroup();
            System.out.println("Group name:" + peerGroup.getPeerGroupName());
            System.out.println("Group id:" + peerGroup.getPeerGroupID());
            System.out.println("Peer name:" + peerGroup.getPeerName());
            System.out.println("Peer id:"+ peerGroup.getPeerID());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}
