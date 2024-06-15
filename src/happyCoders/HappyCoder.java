package happyCoders;

import static java.lang.StringTemplate.STR;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import happyCoders.records.Point;
import happyCoders.records.Point2;
import happyCoders.sealed.Rectangle;
import happyCoders.virtualThreads.Task;

/**
 * Created by krist on Jun 12, 2024.
 *
 * https://www.happycoders.eu/de/java
 */
public class HappyCoder {

	private static final String MONDAY = "1";
	private static final String TUESDAY = "2";
	private static final String WEDNESDAY = "3";
	private static final String THURSDAY = "4";
	private static final String FRIDAY = "5";
	private static final String SATURDAY = "6";
	private static final String SUNDAY = "7";

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InterruptedException, ExecutionException {
		
		
		
		
		
		/*****************************************************************************************************************************************************************************
		 * 
		 *    Switch Expressions (Java 14)
		 *  
		 *    Kombination von Pfeilnotation und Switch-Ausdruck
		 *    
		 */
		String day = "3";
		
		int numLetters = switch (day) {
		  case MONDAY, FRIDAY, SUNDAY -> 6;							// Rückgabewert direkt hinter den Pfeil
		  case TUESDAY                -> 7;							// Rückgabewert direkt hinter den Pfeil
		  case THURSDAY, SATURDAY     -> (int) Math.pow(2, 3);		// Rückgabewert als Ausdruck oder Methodenaufruf hinter den Pfeil 
		  case WEDNESDAY              -> {							// Rückgabewert aus einem Code-Block heraus mit yield zurückgeben
		    int three = 1 + 2;
		    yield three * three;									// yield ist ein sogenanntes "contextual keyword" und damit nur im Kontext eines switch-Ausdrucks von Bedeutung
		  }
		  default -> throw new IllegalStateException("Unknown day: " + day);
		}; 
		
		System.out.println(Integer.valueOf(numLetters));
		
		
		
		
		
		/*****************************************************************************************************************************************************************************
		 * 
		 *    Text Blocks  (Java 15)
		 *  
		 *    Mehrzeilige Strings
		 *
		 */
		String sql = """
			      SELECT id, firstName, lastName
			      FROM Employee
			      WHERE departmentId = "IT"
			    ORDER BY lastName, firstName""";
		
		// Backslash  		\  	kein Zeilenumbruch
		// Escape-Sequenz 	\s  Leerzeichen beibehalten
		
		String sql2 = """
			          SELECT id, firstName, lastName
			          FROM Employee
			          \s\s\sWHERE departmentId = "IT"
			        ORDER BY lastName, firstName\
			  """;
		
		System.out.println(sql);
		System.out.println();
		System.out.println(sql2);
		
		
		
		
		
		/*****************************************************************************************************************************************************************************
		 *    Records (Java 16) 
		 * 
		 *    Records Sie sind implizit final, man kann also NICHT von ihnen erben (entends)
		 *    
		 *    ABER, wir können Interfaces implementieren
		 *  
		 */
		Point p = new Point(5, 10);
		int x = p.x();
		int y = p.y();
		
		Point2 p1 = new Point2(8, 4);
		Point2 p2 = new Point2(4, 3);
		if (p1.equals(p2)) {
		  // ...
		}
		
		double distance = p1.distanceTo(p2);
		
		
		
		/* 
		Lokale Records
		  
		  Records dürfen auch lokal (d. h. innerhalb von Methoden) definiert werden. Das kann insbesondere dann hilfreich sein,
		  wenn man Zwischenergebnisse mit mehreren zusammengehörigen Variablen speichern möchte.
		  
		  Records dürfen auch innerhalb von inneren Klassen definiert werden.
		  
		 
		public Point findFurthestPoint(Point origin, Point... points) {
			
			  record PointWithDistance(Point point, double distance) {}
			  
			  List<PointWithDistance> pointsWithDistance = new ArrayList<>();
			  for (Point point : points) {
			    double distance = origin.distanceTo(point);
			    pointsWithDistance.add(new PointWithDistance(point, distance));
			  }

			  PointWithDistance furthestPointWithDistance = Collections.max(
			      pointsWithDistance,
			      Comparator.comparing(PointWithDistance::distance));

			  return furthestPointWithDistance.point();
		}
		*/
		
		

		
	
		/*****************************************************************************************************************************************************************************
		 *    Sealed Classes and Interfaces (Java 17) 
		 *  
		 */
		
		// sealed, non-sealed, permits sind 'Contextual Keywords' somit haben diese nur in einem bestimmten Kontext ihre Bedeutung
		
		// Lokale Klassen (also innerhalb von Methoden definierte Klassen) dürfen versiegelte Klassen nicht erweitern.
		
		// Bei instanceof-Tests prüft der Compiler, ob die Klassenhierarchie es zulässt, dass der Check jemals true ergeben kann.
		// Ist das nicht der Fall, meldet der Compiler einen "incompatible types"-Fehler,
		
		Rectangle rectangle = new Rectangle();
		System.out.println(rectangle.getClass().isSealed());
		System.out.println(rectangle.getClass().getSuperclass().getPermittedSubclasses());
		
		
		
		
		/*****************************************************************************************************************************************************************************
		 *    Unnamed Patterns and Variables (Java 22) 
		 *  
		 */
				
		// Unbenötigte Variablen müssen nicht mehr bennant werden, und dürfen stattdessen den Unterstrich (_) verwenden
		try {
			  int number = Integer.parseInt(MONDAY);
			} catch (NumberFormatException _) {
			  System.err.println("Not a number");
		}
		
		/* Unbenanntes Pattern Variable
		if (object instanceof Point(int x1, int _)) {
			System.out.println("object is a position, x = " + x1);
		}
		
		
		// Unbenanntes Pattern
		Object object = null;
		if (object instanceof Point(int x1, _)) {
			  System.out.println("object is a position, x = " + x1);
		}
		
		// Unbenannte Pattern-Variablen und Pattern Matching for Switch
		switch (obj) {
			case Byte _, Short _, Integer _, Long _ -> System.out.println("Integer number");
			case Float _, Double _                  -> System.out.println("Floating point number");

			default -> System.out.println("Not a number");
		}
		*/
		
		
		
		
		
		
		/*****************************************************************************************************************************************************************************
		 *    Local-Variable Type Inference (Java 10)
		 *  
		 */
		
		// var httpClient = HttpClient.newBuilder().build();
		
		
		
		
			
		
		/*****************************************************************************************************************************************************************************
		 *    String Templates (Java 22 Preview) 
		 * 
		 * 	Breaking News: Am 05.04.2024 hat Gavin Bierman bekanntgegeben, dass String Templates in der hier beschriebenen Form nicht veröffentlicht werden. Es besteht Einigkeit darüber,
		 *  dass das Design geändert werden soll, es besteht allerdings kein Konsens darüber, wie es geändert werden soll. Die Sprachentwickler wollen sich nun Zeit nehmen, das Design 
		 *  zu überarbeiten. String Templates werden daher in Java 23 nicht weiter enthalten sein, auch nicht mit --enable-preview.
		 * 
		 */
		int a = 5;
		int b = 10;
		double c = 5.81;
		double d = 10.45;
		
		// String Template Processor
		String interpolated_STR = STR."\{a} times \{b} = \{Math.multiplyExact(a, b)}";
	
		/*
		// FMT Template Processor
		// Dieser wertet den Platzhaltern vorangestellte Formatierungsangaben – wie wir sie auch von String.format() kennen – aus.
		String interpolated_FMT = FMT."%.2f\{c} times %.2f\{d} = %.2f\{c * d}";
		
		// SQL Template Processor
		String searchQuery = "name";
		Statement statement = SQL."""
				    SELECT * FROM User u
				    WHERE u.userName LIKE '%\{searchQuery}%'""";
		*/
		
		
		
		
		
		/*****************************************************************************************************************************************************************************
		 *    / Statements before super(…) (Flexible Constructor Bodies) (Java 22 Preview) 
		 *    
		 *    Der Block vor dem Aufruf von super(...) oder this(...) wird Prolog genannt.
		 *    
		 *    Code nach dem Aufruf von super(...) oder this(...) oder Code in einem Konstruktor ohne Aufruf von super(...) oder this(...) wird als Epilog bezeichnet.
		 *  
		 */
		
		// Use Case 1: Validierung von Parametern
		// Use Case 2: Berechnung eines Arguments, das an mehrere Parameter übergeben wird
		// Use Case 3: Aufruf einer überschriebenen Methode im Super-Konstruktor
		
		/*
		public class Square extends Rectangle {
			  public Square(Color color, int area) {
			    if (area < 0) throw new IllegalArgumentException();  // ⟵ Validation before `this`
			    this(color, Math.sqrt(area));
			  }

			  private Square(Color color, double sideLength) {
			    super(color, sideLength, sideLength);
			  }
		}
		
		
		public Square(Color color, int area) {
			  if (area < 0) throw new IllegalArgumentException();  // ⟵ Validation before `super`
			  double sideLength = Math.sqrt(area);                 // ⟵ Calculation before `super`
			  super(color, sideLength, sideLength);
		}
		*/
		
		
		
		
		
		/*****************************************************************************************************************************************************************************
		 *    Virtual Threads (Java 21)
		 *     
		 *  
		 */
		
		// Ein Betriebssystem-Thread reserviert 1 MB für den Stack im Vorraus und ist darum sehr resourcenhungrig
		// Virtuelle Threads arbeiten mit Carrier Threads die als ForkJoinPool implementiert sind.
		// Diese Charakteristika des 'Task stealing from other Queues Thread' resultiert in einer enormen Performance Steigerung.
		
		
		// Executors.newVirtualThreadPerTaskExecutor() erstellt pro Task einen neuen virtuellen Thread
		try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
			  List<Task> tasks = new ArrayList<>();
			  for (int i = 0; i < 1_000; i++) {
			    tasks.add(new Task(i));
			  }

			  long time = System.currentTimeMillis();

			  List<Future<Integer>> futures = executor.invokeAll(tasks);

			  long sum = 0;
			  for (Future<Integer> future : futures) {
			    sum += future.get();
			  }

			  time = System.currentTimeMillis() - time;

			  System.out.println("sum = " + sum + "; time = " + time + " ms");
			}
		
		
		// Exciplites starten von virtuellen Threads
		Thread.startVirtualThread(() -> {
			  // code to run in thread
		});
		Thread.ofVirtual().start(() -> {
			  // code to run in thread
		});
		Thread.ofPlatform().start(() -> {
			// code to run in platform-thread
		});
		
		Thread.currentThread().isVirtual();
		
		
		
		// Virtuelle Threads in Spring
		/*
		@Bean(TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME)
		public AsyncTaskExecutor asyncTaskExecutor() {
		  return new TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor());
		}

		@Bean
		public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
		  return protocolHandler -> {
		    protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
		};
		*/
		}
	
	
	
	
	
	
	
	/*****************************************************************************************************************************************************************************
	 * 
	 *    Structured Concurrency (Java 21 Preview)
	 *    
	 *    
	 *    Konzept, das die Implementierung, Lesbarkeit und Wartbarkeit von Code für die Aufteilung einer Aufgabe in Teilaufgaben und deren nebenläufige Abarbeitung erheblich verbessert.
	 *
	 *	  - Dazu führt sie mit der Klasse 'StructuredTaskScope' eine Kontrollstruktur ein, die einen klaren Scope definiert, an dessen Anfang die Threads der Teilaufgaben starten und 
	 *	    an dessen Ende die Threads der Teilaufgaben enden,
	 * 
     *    - die eine saubere Fehlerbehandlung ermöglicht
     *  
     *    - und die einen sauberen Abbruch von Teilaufgaben erlaubt, deren Ergebnisse nicht mehr benötigten werden.
	 *
	 
	// Shutdown on Failure-Policy
	Invoice createInvoice(int orderId, int customerId, String language) throws InterruptedException, ExecutionException {
	  try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
	    Subtask<Order> orderSubtask = 
	        scope.fork(() -> orderService.getOrder(orderId));
	
	    Subtask<Customer> customerSubtask = 
	        scope.fork(() -> customerService.getCustomer(customerId));
	
	    Subtask<InvoiceTemplate> invoiceTemplateSubtask =
	        scope.fork(() -> invoiceTemplateService.getTemplate(language));
	
	    scope.join();
	    scope.throwIfFailed(); <--
	
	    Order order = orderSubtask.get();
	    Customer customer = customerSubtask.get();
	    InvoiceTemplate template = invoiceTemplateSubtask.get();
	
	    return Invoice.generate(order, customer, template);
	  }
	}
	
	
	// Shutdown on Success-Policy
	AddressVerificationResponse verifyAddress(Address address) throws InterruptedException, ExecutionException {
	  try (var scope = new ShutdownOnSuccess<AddressVerificationResponse>()) {
	    scope.fork(() -> verificationService.verifyViaServiceA(address));
	    scope.fork(() -> verificationService.verifyViaServiceB(address));
	    scope.fork(() -> verificationService.verifyViaServiceC(address));
	
	    scope.join();
	
	    return scope.result(); <--
	  }
	}
	*/
	
	
	
	
	
	
	/*****************************************************************************************************************************************************************************
	 * 
	 *    Scoped Value (Java 21 Preview)
	 *    
	 *    Scoped Values sind eine Form von impliziten Methodenparametern, die es ermöglichen, einen oder mehrere Werte (d. h. beliebige Objekte) 
	 *    an eine oder mehrere weit entfernte Methoden zu übergeben, ohne sie als explizite Parameter zu jeder Methode in der Aufrufkette hinzufügen zu müssen.
	 * 
	 *	  Scoped Values werden in der Regel als öffentliche statische Felder angelegt, so dass sie von beliebigen Methoden aus abrufbar sind.
	 *
	 *    Verwenden mehrere Threads dasselbe statische ScopedValue-Feld, dann kann dieses aus Sicht eines jeden Threads einen anderen Wert enthalten.
	 *
	 *    Falls du mit ThreadLocal-Variablen vertraut bist, kommt dir das sicher bekannt vor. Tatsächlich stellen Scoped Values eine moderne Alternative für Thread Locals dar.
	 *     
	*
	
	class Server {
		  public final static ScopedValue<User> LOGGED_IN_USER = ScopedValue.newInstance();
		 
		  private void serve(Request request) {
		    // ...
		    User loggedInUser = authenticateUser(request);
		    ScopedValue.where(LOGGED_IN_USER, loggedInUser)
		               .run(() -> restAdapter.processRequest(request));
		    // ...
		  }
	}
	
	
	*/
	


}
