/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.inchat.java;

import java.util.UUID;
import org.inchat.common.*;
import org.inchat.protocol.*;

/**
 *
 */
public class InchatJava implements MessageReceiver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InchatJava self = new InchatJava();
    }
    private final ProtocolManager conn;
    private final Participant alice;
    private Participant bob;

    /**
     * This is an example how a User could send a message to another
     */
    public InchatJava() {

        // This is me
        alice = new Participant(UUID.randomUUID());

        // Access the connection Manager
        conn = ProtocolManager.getManager();

        // Listen for incomming messages
        conn.listen(alice, this);

        // Create a new Message
        EncryptedMessage emsg = CommonLib.encrypt(new Message("My fancy Text:)"));

        // Lookup Bob, send Message to the DHT
        conn.send(emsg, ProtocolManager.DHT_USER);
    }

    @Override
    public void onMessage(Message msg) {
        switch (msg.type) {
            case LOOKUP_ANSWER:
                // We received the UUID from BOB
                bob = new Participant(new UUID(msg.getContent()[0], msg.getContent()[1]));

                // Create a new Message
                EncryptedMessage emsg = CommonLib.encrypt(new Message("Hi Bob :)"));
                
                // Send the Message to Bob
                conn.senc(emsg, bob);
                break;
            case MESSAGE:
                // Bob's Answers
                System.out.println(msg.getContent());
                break;
            default:
                throw new IllegalStateException("Unknown MSG-Type received");
        }
    }
}
