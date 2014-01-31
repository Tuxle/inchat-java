/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inchat.java;

import java.util.UUID;
import org.inchat.common.*;

/**
 *
 */
public class InchatJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InchatJava self = new InchatJava();
    }

    public InchatJava() {
        
        // This is me
        Participant alice = new Participant(new UUID(
                (int)(Math.random()*1024*1024)
                , (int)(Math.random()*1024*1024)
        ));
        
        Connection conn = InChatTransport.connect("remotehost");
        
    }
}
