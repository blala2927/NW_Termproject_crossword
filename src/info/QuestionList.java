package info;

import server.DB;

public class QuestionList {
	private static QuestionList questionList;
	private int numberOfQuestion = 22;
	private String q[] = new String[numberOfQuestion];
	public QuestionList() {
		makeQuestion();
	}
	
	public static QuestionList getInstance() {
		if (questionList == null) {
			synchronized(QuestionList.class) {
				if (questionList == null) {
					questionList = new QuestionList();
				}
			}
		}
		return questionList;
	}
	
	public String getQustion(int i) {
		return q[i];
	}
	
	public void makeQuestion() {
		q[0] = "<html>We simply mean it a computer or other tool can be used for.</html>";
		q[1] = "<html>____ is a standard used to define a method of exchanging data over"
				+ " a computer network, such as local area network, Internet, Intranet, etc.</html>";
		q[2] = "<html>The protocols of the layer provide host-to-host"
				+ " communication services for applications.</html>";
		q[3] = "<html>The process of sending and propagating an analogue or digital"
				+ " information signal over a physical point-to-point or point-to-multipoint"
				+ " transmission medium, either wired, optical fiber or wireless.</html>";
		q[4] = "<html>____ routing protocols have a complete picture of the network topology.</html>";
		q[5] = "It conceptually divides computer network architecture "
				+ "into 7 ____ in a logical progression.</html>";
		q[6] = "<html>____ is a dynamic protocol used to find the best route or path"
				+ " from end-to-end (source to destination) over a network by using a routing metric/hop count algorithm.</html>";
		q[7] = "<html>____, sometimes called simply \"the Net,\" is a worldwide system of computer networks.</html>";
		q[8] = "<html>____ propagate reachability information to all AS-internal routers.</html>";
		q[9] = "<html>The ____ of a network specifies how long it takes for a bit of data "
				+ "to travel across the network from one node or endpoint to another.</html>";
		q[10] = "<html>____ is a cyber-attack where the perpetrator seeks to make "
				+ "a machine or network resource unavailable to its intended users by temporarily or indefinitely "
				+ "disrupting services of a host connected to the Internet.</html>";
		q[11] = "<html>It uses a link state routing (LSR) algorithm and falls into the group of interior"
				+ " gateway protocols (IGPs), operating within a single autonomous system (AS).</html>";
		q[12] = "<html>A network ____ is an internal endpoint for sending or receiving"
				+ " data at a single node in a computer network.</html>";
		q[13] = "<html>____ provides reliable, ordered, and error-checked delivery of "
				+ "a stream of octets between applications running on hosts communicating by an IP network.</html>";
		q[14] = "<html>The _____ is a term that is used to describe a segment of data "
				+ "sent from one computer or device to another over a network.</html>";
		q[15] = "<html>____ is used to send short messages called datagrams "
				+ "but overall, it is an unreliable, connectionless protocol.</html>";
		q[16] = "<html>____’s algorithm to find the shortest path between a and b.</html>"
				+ " It picks the unvisited vertex with the lowest distance, calculates"
				+ "the distance through it to each unvisited neighbor, and updates the neighbor's distance if smaller."
				+ " Mark visited (set to red) when done with neighbors.</html>";
		q[17] = "<html>The ____ delay, is the time it takes a bit to propagate from one router to the next.</html>";
		q[18] = "<html>In computing, ____ is the bit-rate of available or consumed information capacity"
				+ " expressed typically in metric multiples of bits per second.</html>";
		q[19] = "<html>____ routing protocol is one of the two major classes of intra domain routing protocols,"
				+ " the other major class being the link-state protocol</html>";
		q[20] = "<html>A _____ precedes the data or control signals and describes"
				+ "something about the file or transmission unit.</html>";
		q[21] = "<html>On the Internet, an end user can determine the ____ to and "
				+ "from an IP (Internet Protocol) address by pinging that address.</html>";

	}
}