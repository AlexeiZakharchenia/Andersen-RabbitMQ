import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {
    private static final String QUEUE_NAME = "new_queue";

    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME,false, false,false,null);
            String message = "Hello world!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Sended:" + message);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
