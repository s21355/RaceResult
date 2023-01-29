package RaceResultTest;

import RaceResult.Client;
import RaceResult.Message;
import RaceResult.Race;
import RaceResult.RaceResultService;
import org.junit.Test;
import org.mockito.Mockito;
import org.easymock.EasyMock;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.easymock.EasyMock.*;
import static org.mockito.Mockito.verify;

public class RaceResultTest {

    @Test
    public void testAddSubscriber() {

        Client mockClient = Mockito.mock(Client.class);
        RaceResultService service = new RaceResultService();

        service.addSubscriber(mockClient);
        assertTrue(service.clients.contains(mockClient));
    }

    @Test
    public void testRemoveSubscriber() {

        Client mockClient = Mockito.mock(Client.class);
        RaceResultService service = new RaceResultService();
        service.addSubscriber(mockClient);
        service.removeSubscriber(mockClient);
        assertFalse(service.clients.contains(mockClient));
    }

    @Test
    public void testSend() {

        Client mockClient = createMock(Client.class);
        RaceResultService service = new RaceResultService();
        Race mockRace = Mockito.mock(Race.class);

        service.addSubscriber(mockClient);
        mockClient.receive(mockRace);
        EasyMock.expectLastCall();
        replay(mockClient);

        service.send(mockRace);
        EasyMock.verify(mockClient);
    }

    @Test
    public void testRemoveSubscriberTwo() {
        RaceResultService service = new RaceResultService();
        Client client = Mockito.mock(Client.class);
        service.addSubscriber(client);
        service.removeSubscriber(client);
        Message message = new Race("Race 1", "Runner 1");
        service.send(message);
        verify(client, Mockito.never()).receive(Mockito.any(Message.class));
    }

    @Test
    public void testSendEasyMock() {
        RaceResultService service = new RaceResultService();
        Client client1 = createMock(Client.class);
        Client client2 = createMock(Client.class);
        service.addSubscriber(client1);
        service.addSubscriber(client2);
        Message message = new Race("Race 1", "Runner 1");
        client1.receive(message);
        client2.receive(message);
        replay(client1);
        replay(client2);
        service.send(message);
    }
}