package mini;

// ============================================
// DISASTER RELIEF RESOURCE ALLOCATION SYSTEM
// ============================================
import java.util.*;
import java.util.stream.Collectors;
import java.time.*;
import java.time.format.*;
import java.io.InputStream;

// ====================================
// AREA CLASS
// ====================================
class Area {
	String name;
	int urgency;
	int distance;
	String resourceNeeded;
	String resourceType;
	int resourceQty;
	int populationAffected;
	boolean isAccessible;
	String disasterType;
	String contactPerson;
	long contactNumber;
// Key new fields only
	LocalDateTime reportedTime;
	int estimatedResponseTime;
	String severity;
	int casualties;
	boolean medicalEmergency;

	public Area(String name, int urgency, int distance, String resourceNeeded, int populationAffected,
			boolean isAccessible, String disasterType, String contactPerson, long contactNumber, int casualties,
			boolean medicalEmergency) {
		this.name = name;
		this.urgency = urgency;
		this.distance = distance;

		this.resourceNeeded = resourceNeeded;
		this.resourceType = resourceNeeded;
		this.resourceQty = populationAffected / 10;
		this.populationAffected = populationAffected;
		this.isAccessible = isAccessible;
		this.disasterType = disasterType;
		this.contactPerson = contactPerson;
		this.contactNumber = contactNumber;
		this.reportedTime = LocalDateTime.now();
		this.estimatedResponseTime = calculateResponseTime();
		this.severity = determineSeverity();
		this.casualties = casualties;
		this.medicalEmergency = medicalEmergency;
	}

	private int calculateResponseTime() {
		int baseTime = distance * 3;
		if (!isAccessible)
			baseTime += 30;
		if (medicalEmergency)
			baseTime -= 10;
		return Math.max(baseTime, 10);
	}

	private String determineSeverity() {
		int score = 0;
		if (urgency == 1)
			score += 30;
		else if (urgency == 2)
			score += 20;
		else
			score += 10;
		if (casualties > 10)
			score += 30;
		else if (casualties > 5)
			score += 20;
		else if (casualties > 0)
			score += 10;
		if (medicalEmergency)
			score += 20;
		if (!isAccessible)
			score += 15;
		if (score >= 70)
			return "Critical";
		else if (score >= 50)
			return "High";
		else if (score >= 30)
			return "Medium";
		else
			return "Low";
	}

	public String getFormattedTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM HH:mm");
		return reportedTime.format(formatter);
	}

	public void display() {
		System.out.println(
				String.format("%-15s %-10s %-12d %-10d %-18s", name, severity, distance, urgency, resourceNeeded));
	}

	@Override
	public String toString() {
		return name + " | Severity: " + severity + " | Urgency: " + urgency + " | Distance: " + distance
				+ " km | Casualties: " + casualties +

				" | Need: " + resourceNeeded + " | People: " + populationAffected;
	}
}

// ====================================
// VEHICLE CLASS
// ====================================
class Vehicle {
	String vehicleId;
	String type;
	boolean isAvailable;
	String assignedTo;

	public Vehicle(String vehicleId, String type) {
		this.vehicleId = vehicleId;
		this.type = type;
		this.isAvailable = true;
	}

	public void assign(String areaName) {
		this.assignedTo = areaName;
		this.isAvailable = false;
	}

	@Override
	public String toString() {
		return vehicleId + " (" + type + ") - " + (isAvailable ? "Available" : "Assigned to " + assignedTo);
	}
}

// ====================================
// AREA INPUT MODULE
// ====================================
class AreaInputModule 
{
  public ArrayList<Area> getAreaData() 
  {
Scanner sc = new Scanner(System.in);
ArrayList<Area> areaList = new ArrayList<>();
System.out.println("\n============================================================");
System.out.println("|| 📥 AREA INPUT & ASSESSMENT MODULE");
System.out.println("============================================================");
int n;
while (true) {
System.out.print("Enter number of affected areas: ");
try {
n = Integer.parseInt(sc.nextLine());
if (n <= 0) {
System.out.println("⚠ Value must be positive.");

continue;
}
break;
} catch (Exception e) {
System.out.println("⚠ Invalid input, please enter a number.");
}
}
for (int i = 0; i < n; i++) {
System.out.println("\n------------------------------------------------------------");
System.out.println("--- Enter details for Area " + (i + 1) + " ---");
String name;
while (true) {
System.out.print("Area name: ");
name = sc.nextLine().trim();
// ✅ allow only alphabets & spaces (min 2 letters)
if (name.matches("[a-zA-Z ]{2,}")) {
break;
} 
else 
{
System.out.println("❌ Invalid name! Only alphabets and spacesallowed.");
}
}
System.out.print("Disaster Type (Flood/Earthquake/Fire/etc): ");
String disasterType = sc.nextLine().trim();
int urgency;

while (true) 
{
System.out.print("Urgency (1=High, 2=Medium, 3=Low): ");
try {
urgency = Integer.parseInt(sc.nextLine());
if (urgency < 1 || urgency > 3) {
System.out.println("⚠ Enter value between 1–3.");
continue;
}
break;

} catch (Exception e) {
System.out.println("⚠ Enter a valid number.");
}
}
int distance;
while (true) {
System.out.print("Distance from warehouse (km): ");
try {
distance = Integer.parseInt(sc.nextLine());
if (distance < 0) {
System.out.println("⚠ Distance cannot be negative.");

continue;
}
break;

} catch (Exception e) {
System.out.println("⚠ Enter a valid number.");
}
}
int populationAffected;
while (true) {
System.out.print("Population affected: ");
try {
populationAffected = Integer.parseInt(sc.nextLine());
if (populationAffected < 0) {
System.out.println("⚠ Cannot be negative.");
continue;
}
break;

} catch (Exception e) {
System.out.println("⚠ Enter a valid number.");
}
}
int casualties;
while (true) {
System.out.print("Number of casualties: ");
try {
casualties = Integer.parseInt(sc.nextLine());
if (casualties < 0) {
System.out.println("⚠ Cannot be negative.");
continue;
}
break;

} catch (Exception e) {
System.out.println("⚠ Enter a valid number.");
}
}
boolean medicalEmergency;
while (true) {
System.out.print("Medical emergency? (yes/no): ");
String in = sc.nextLine().toLowerCase();
if (in.equals("yes") || in.equals("y")) { medicalEmergency = true;
break; }
if (in.equals("no") || in.equals("n")) { medicalEmergency = false;
break; }
System.out.println("⚠ Enter yes or no.");
}
System.out.print("Resources needed (Medical Kits/Blankets/Food Packets/Water Bottles): ");
String resource = sc.nextLine().trim();
boolean isAccessible;

while (true) {
System.out.print("Road accessible? (yes/no): ");
String in = sc.nextLine().toLowerCase();
if (in.equals("yes") || in.equals("y")) { isAccessible = true; break;
}
if (in.equals("no") || in.equals("n")) { isAccessible = false; break;
}
System.out.println("⚠ Enter yes or no.");
}
String contactPerson;
while (true) {
System.out.print("Contact person: ");
contactPerson = sc.nextLine().trim();
// ✅ allow only alphabets & spaces (min 2 letters)
if (contactPerson.matches("[a-zA-Z ]{2,}")) {
break;
} else {
System.out.println("❌ Invalid name! Only alphabets and spaces allowed.");
}
}
long contactNumber;
while (true) {
System.out.print("Contact number: ");
try {
contactNumber = Long.parseLong(sc.nextLine());
if (contactNumber < 0) {
System.out.println("⚠ Cannot be negative.");
continue;
}
break;

} catch (Exception e) {
System.out.println("⚠ Enter digits only.");
}
}
Area newArea = new Area(
name, urgency, distance, resource,
populationAffected, isAccessible, disasterType,
contactPerson, contactNumber, casualties, medicalEmergency
);
areaList.add(newArea);
System.out.println("------------------------------------------------------------");
System.out.println("✓ Area registered | Severity: " + newArea.severity +
" | ETA: " + newArea.estimatedResponseTime + " min");
}

System.out.println("============================================================");
System.out.println("|| Total Areas Registered: " + areaList.size());
System.out.println("============================================================\n");
return areaList;
}
}

//====================================
// PRIORITY MODULE (DSA: Max-Heap/Priority Queue)
// ====================================
class PriorityModule {
class MaxHeap {
private ArrayList<Area> heap = new ArrayList<>(); // Underlying data structure
// Comparator logic defining the priority order
private int prioritize(Area a, Area b) {
// Priority 1: Critical Severity (Highest)
if (a.severity.equals("Critical") && !b.severity.equals("Critical"))
return -1;
if (!a.severity.equals("Critical") && b.severity.equals("Critical"))
return 1;
// Priority 2: Medical Emergency
if (a.medicalEmergency && !b.medicalEmergency) return -1;
if (!a.medicalEmergency && b.medicalEmergency) return 1;
// Priority 3: Casualties (More casualties = Higher priority)
if (a.casualties != b.casualties)
return Integer.compare(b.casualties, a.casualties);
// Priority 4: Urgency (Lower number = Higher priority)
if (a.urgency != b.urgency)
return Integer.compare(a.urgency, b.urgency);
// Priority 5: Distance (Shorter distance = Higher priority)
return Integer.compare(a.distance, b.distance);
}
void insert(Area area) {
heap.add(area);
int i = heap.size() - 1;
// Bubble up (Heap property restoration)
while (i > 0 && prioritize(heap.get(i), heap.get(parent(i))) < 0) {
swap(i, parent(i));
i = parent(i);
}
}
Area extractHighestPriority() {

if (heap.isEmpty()) return null;
Area top = heap.get(0);
heap.set(0, heap.get(heap.size() - 1));
heap.remove(heap.size() - 1);
heapify(0); // Bubble down
return top;
}
boolean isEmpty() { return heap.isEmpty(); }
// Helper methods for heap structure
private void heapify(int i) {
int left = left(i), right = right(i);
int highest = i;
if (left < heap.size() && prioritize(heap.get(left), heap.get(highest)) <
0) highest = left;
if (right < heap.size() && prioritize(heap.get(right), heap.get(highest))
< 0) highest = right;
if (highest != i) { swap(i, highest); heapify(highest); }
}
private void swap(int i, int j) { Area temp = heap.get(i); heap.set(i,
heap.get(j)); heap.set(j, temp); }
private int parent(int i) { return (i - 1) / 2; }
private int left(int i) { return 2 * i + 1; }
private int right(int i) { return 2 * i + 2; }
}

	public ArrayList<Area> getPriorityOrder(ArrayList<Area> areas) {
		ArrayList<Area> ordered = new ArrayList<>();
		MaxHeap heap = new MaxHeap();
		for (Area a : areas)
			heap.insert(a);
		while (!heap.isEmpty()) {
			ordered.add(heap.extractHighestPriority());
		}
		return ordered;
	}

public void showPriorityList(ArrayList<Area> areas) {
System.out.println("\n" + "=".repeat(80));
System.out.println(String.format("%-80s", "|| 🧭 PRIORITY DISPATCH ORDER (Max-Heap Result)"));
System.out.println("=".repeat(80));
// Header with consistent formatting
System.out.println(String.format("%-5s %-15s %-10s %-10s %-12s %-20s",
"Order", "Area", "Severity", "Dist(km)", "Urgency", "Resource"));
System.out.println("-".repeat(80));
for (int i = 0; i < areas.size(); i++) {
Area a = areas.get(i);
System.out.println(String.format("%-5d %-15s %-10s %-10d %-12d %-20s",
(i + 1), a.name, a.severity, a.distance, a.urgency,
a.resourceNeeded));
}
System.out.println("=".repeat(80) + "\n");
}
}

// ====================================
// STATUS MODULE (DSA: Custom Hash Table)
// ====================================
class AreaStatusEntry {
	String areaName;
	String status;
	LocalDateTime lastUpdated;

	public AreaStatusEntry(String areaName, String status) {
		this.areaName = areaName;
		this.status = status;
		this.lastUpdated = LocalDateTime.now();
	}
}

class MyHashTable {
	private final int SIZE = 50;
	private AreaStatusEntry[] table; // Array for the hash table buckets

	public MyHashTable() {
		table = new AreaStatusEntry[SIZE];
	}

// Hashing function (Division Method)
private int getHash(String key) {
int sum = 0;
for (int i = 0; i < key.length(); i++) 
{ 
	sum += key.charAt(i); 
	} // Convert string to numeric key
return sum % SIZE; // Division method
}

// Insert or Update (Collision handling: Linear Probing)
	public void put(String key, String value) {
		int hash = getHash(key);
		for (int i = 0; i < SIZE; i++) {
			int index = (hash + i) % SIZE; // Linear Probing step
			if (table[index] == null) {
				table[index] = new AreaStatusEntry(key, value);
				return;
			}
			if (table[index].areaName.equals(key)) {
				table[index].status = value; // Update existing entry
				table[index].lastUpdated = LocalDateTime.now();
				return;
			}
		}
	}

// Retrieve value by key (Linear Probing)
public String get(String key) {
int hash = getHash(key);
for (int i = 0; i < SIZE; i++) {
int index = (hash + i) % SIZE;

if (table[index] == null) return null; // Found an empty spot, key not present
if (table[index].areaName.equals(key)) return table[index].status;
}
return null;
}

public void displayAll() {
System.out.println("\n" + "=".repeat(60));
System.out.println(String.format("%-60s", "|| ✅ DELIVERY STATUS SUMMARY (MyHashTable)"));
System.out.println("=".repeat(60));
System.out.println(String.format("%-30s %-30s", "AREA NAME", "STATUS"));
System.out.println("-".repeat(60));
for (int i = 0; i < SIZE; i++) {
if (table[i] != null) {
System.out.println(String.format("%-30s %-30s",
table[i].areaName, table[i].status));
}
}
System.out.println("=".repeat(60) + "\n");
}

	public void displayStats() {
		int total = 0, dispatched = 0, pending = 0;
		for (int i = 0; i < SIZE; i++) {
			if (table[i] != null) {
				total++;
				if (table[i].status.contains("Dispatched"))
					dispatched++;
				else if (table[i].status.contains("Pending"))
					pending++;
			}
		}
		System.out.println("=== RESPONSE STATISTICS ===");
		System.out.println("Total Areas: " + total);
		System.out.println("Dispatched: " + dispatched + " (" + (total > 0 ? (dispatched * 100 / total) : 0) + "%)");
		System.out.println("Pending: " + pending + " (" + (total > 0 ? (pending * 100 / total) : 0) + "%)");
		System.out.println("===========================");
	}
}

class StatusModule {
	MyHashTable areaStatus = new MyHashTable();

	public void initializeStatus(ArrayList<Area> areas) {
		for (Area area : areas) {
			areaStatus.put(area.name, "Registered");
		}
	}

	public void updateStatus(String name, String status) {
		areaStatus.put(name, status); // O(1) average time complexity
	}

	public void showAllStatus() {
		areaStatus.displayAll();
	}

	public void showStats() {
		areaStatus.displayStats();
	}
}

// ====================================
// ALLOCATION MODULE (DSA: Custom Queue, HashMap)
// ====================================
class AllocationModule {
// Custom Queue implementation using linked nodes
	static class Node {
		Area data;
		Node next;

		Node(Area data) {
			this.data = data;
			this.next = null;
		}
	}

	static class MyQueue {
		Node front, rear;

		MyQueue() {
			front = rear = null;
		}

		void enqueue(Area data) {
			Node newNode = new Node(data);
			if (rear == null) {
				front = rear = newNode;
				return;
			}
			rear.next = newNode;
			rear = newNode;
		}

		Area dequeue() {
			if (front == null)
				return null;
			Area temp = front.data;
			front = front.next;
			if (front == null)
				rear = null;
			return temp;
		}

		boolean isEmpty() {
			return front == null;
		}

		int size() {
			int count = 0;
			Node current = front;
			while (current != null) {
				count++;
				current = current.next;
			}
			return count;
		}
	}

	static Map<String, Integer> availableStock = new HashMap<>(); // DSA: HashMap for O(1) stock lookup

	static ArrayList<Vehicle> vehicleFleet = new ArrayList<>();
// Initial Stock and Fleet Setup
	static {
		availableStock.put("Medical Kits", 100);
		availableStock.put("Food Packets", 150);
		availableStock.put("Blankets", 80);
		availableStock.put("Water Bottles", 120);
		vehicleFleet.add(new Vehicle("AMB-01", "Ambulance"));
		vehicleFleet.add(new Vehicle("TRK-01", "Truck"));
		vehicleFleet.add(new Vehicle("TRK-02", "Truck"));
		vehicleFleet.add(new Vehicle("HEL-01", "Helicopter"));
	}

public static Map<String, MyQueue> allocateResources(ArrayList<Area> sortedAreas,
StatusModule statusModule) {
MyQueue dispatchedQueue = new MyQueue();

MyQueue pendingQueue = new MyQueue();
System.out.println("\n" + "=".repeat(60));
System.out.println(String.format("%-60s", "|| 📦 RESOURCE ALLOCATION DECISIONS"));
System.out.println("=".repeat(60));
for (Area a : sortedAreas) {
int available = availableStock.getOrDefault(a.resourceType, 0);
Vehicle vehicle = assignVehicle(a);
if (available >= a.resourceQty && vehicle != null) {
// SUCCESS: Dispatch
availableStock.put(a.resourceType, available - a.resourceQty);
vehicle.assign(a.name);
dispatchedQueue.enqueue(a);
statusModule.updateStatus(a.name, "Dispatched - En Route");
System.out.println("\n--- DISPATCHED ---");
System.out.println(String.format("✓ %-15s | Vehicle: %-10s | ETA: %d min", a.name, vehicle.vehicleId, a.estimatedResponseTime));
System.out.println(String.format(" Resources: %d %s", a.resourceQty,
a.resourceType));
System.out.println(String.format(" Contact: %s (%d)",
a.contactPerson, a.contactNumber));
} else {
// FAILURE: Pending
pendingQueue.enqueue(a);
String reason = (available < a.resourceQty) ? "Insufficient Stock" :
"No Vehicle";
statusModule.updateStatus(a.name, "Pending - " + reason);
System.out.println("\n--- PENDING ---");
System.out.println(String.format("✗ %-15s | Reason: %s", a.name,
reason));
System.out.println(String.format(" Required: %d %s | Available: %d",
a.resourceQty, a.resourceType, available));
System.out.println(String.format(" Priority: %s (Urgency: %d)",
a.severity, a.urgency));
}
}
// Final Totals Display
System.out.println("=".repeat(60));
System.out.println(String.format("|| TOTALS: Dispatched: %d | Pending: %d",
dispatchedQueue.size(), pendingQueue.size()));
System.out.println("=".repeat(60) + "\n");
Map<String, MyQueue> result = new HashMap<>();
result.put("dispatched", dispatchedQueue);
result.put("pending", pendingQueue);
return result;
}

	private static Vehicle assignVehicle(Area area) {
// Vehicle assignment logic based on medical/accessibility criteria
		if (area.medicalEmergency) {
			for (Vehicle v : vehicleFleet) {
				if (v.type.equals("Ambulance") && v.isAvailable) {
					return v;
				}
			}
		}
		if (!area.isAccessible) {
			for (Vehicle v : vehicleFleet) {
				if (v.type.equals("Helicopter") && v.isAvailable) {
					return v;
				}
			}
		}
		for (Vehicle v : vehicleFleet) {
			if (v.type.equals("Truck") && v.isAvailable) {
				return v;
			}
		}
		return null;
	}

	public static void restockAndReallocate(MyQueue pendingQueue, StatusModule statusModule) {
		Scanner sc = new Scanner(System.in);
		System.out.println("=== RESTOCKING MODULE ===");
// Show current stock status
		System.out.println("Current Stock:");
		for (Map.Entry<String, Integer> entry : availableStock.entrySet()) {
			System.out.println(" " + entry.getKey() + ": " + entry.getValue());
		}
		System.out.print("Resource type to restock: ");
		String resourceType = sc.nextLine();
		System.out.print("Quantity to add: ");
		int quantity = sc.nextInt();
		int available = availableStock.getOrDefault(resourceType, 0);
		availableStock.put(resourceType, available + quantity); // Update stock
		System.out.println("✓ Restocked: " + resourceType + " (+" + quantity + ")");
		System.out.println(" New Stock: " + availableStock.get(resourceType));
// Reallocate: Try to fulfill pending requests
		MyQueue newPending = new MyQueue();
		int fulfilled = 0;
		while (!pendingQueue.isEmpty()) {
			Area a = pendingQueue.dequeue();
			int avail = availableStock.getOrDefault(a.resourceType, 0);
			Vehicle vehicle = assignVehicle(a);
			if (avail >= a.resourceQty && vehicle != null) {
				availableStock.put(a.resourceType, avail - a.resourceQty);
				vehicle.assign(a.name);
				System.out.println("✓ Re-Dispatched: " + a.name);
				statusModule.updateStatus(a.name, "Dispatched - En Route");
				fulfilled++;
			} else {

				newPending.enqueue(a); // Still pending
			}
		}
		System.out.println("Fulfilled: " + fulfilled + " areas");
		if (!newPending.isEmpty()) {
			System.out.println("Still Pending: " + newPending.size() + " areas");
		}
	}

	public static void showFleet() {
		System.out.println("=== VEHICLE FLEET STATUS ===");
		for (Vehicle v : vehicleFleet) {
			System.out.println(" " + v);
		}
		System.out.println("============================");
	}

	public static void showInventory() {
		System.out.println("=== INVENTORY STATUS ===");
		for (Map.Entry<String, Integer> entry : availableStock.entrySet()) {
			String status = entry.getValue() > 50 ? "✓" : entry.getValue() > 20 ? "!" : "✗";
			System.out.println(String.format(" [%s] %-20s: %3d units", status, entry.getKey(), entry.getValue()));
		}
		System.out.println("========================");
	}

public static void simulateDeliveryCompletion(StatusModule statusModule) {
// Simulates a truck arriving and becoming available
Vehicle deliveredVehicle = null;
Random rand = new Random();
List<Vehicle> assignedVehicles = vehicleFleet.stream()
.filter(v -> !v.isAvailable)
.collect(Collectors.toList());
if (assignedVehicles.isEmpty()) {
System.out.println("No vehicles are currently en route to complete delivery.");
return;
}
deliveredVehicle =
assignedVehicles.get(rand.nextInt(assignedVehicles.size()));
String areaName = deliveredVehicle.assignedTo;
statusModule.updateStatus(areaName, "Delivered - Completed"); // Update Hash Table
deliveredVehicle.assignedTo = null;
deliveredVehicle.isAvailable = true; // Make vehicle available
System.out.println("✓ DELIVERY COMPLETE: " + areaName);
System.out.println(" Vehicle " + deliveredVehicle.vehicleId + " is now available.");

}
}

// ====================================
// MAIN SYSTEM
// ====================================
public class Main {
public static void main(String[] args) {
Scanner sc = new Scanner(System.in);
System.out.println("========================================");
System.out.println(" DISASTER RELIEF MANAGEMENT SYSTEM");
System.out.println("========================================");
// Step 1: Input
AreaInputModule inputModule = new AreaInputModule();
ArrayList<Area> areas = inputModule.getAreaData();
// Step 2: Prioritize (DSA: Max-Heap)
PriorityModule priorityModule = new PriorityModule();
ArrayList<Area> sortedAreas = priorityModule.getPriorityOrder(areas);
priorityModule.showPriorityList(sortedAreas);
// Step 3: Initialize status (DSA: Custom Hash Table)
StatusModule statusModule = new StatusModule();
statusModule.initializeStatus(areas);
// Step 4: Allocate (DSA: Custom Queue, HashMap)
Map<String, AllocationModule.MyQueue> allocationResult =
AllocationModule.allocateResources(sortedAreas, statusModule);
AllocationModule.MyQueue dispatchedQueue =
allocationResult.get("dispatched");
AllocationModule.MyQueue pendingQueue = allocationResult.get("pending");
// Step 5: Display Initial Status
statusModule.showAllStatus();
statusModule.showStats();
AllocationModule.showFleet();
AllocationModule.showInventory();
// Step 6: Operations menu
boolean running = true;
while (running) {
System.out.println("=== OPERATIONS MENU ===");
System.out.println("1. View Status");
System.out.println("2. View Statistics");
System.out.println("3. Restock & Reallocate PENDING");
System.out.println("4. Simulate Delivery Completion");
System.out.println("5. View Fleet");
System.out.println("6. View Inventory");
System.out.println("7. Exit");
System.out.print("Select: ");

int choice = sc.nextInt();
sc.nextLine();
switch (choice) {
case 1:
statusModule.showAllStatus();
break;
case 2:
statusModule.showStats();
break;
case 3:
// Logic to check pending queue before calling restock

if (!pendingQueue.isEmpty()) {

AllocationModule.restockAndReallocate(pendingQueue,
statusModule);
} else {
System.out.println("✓ No requests are pending. Use option 6 to check inventory.");
// Allow restocking even with no pending requests
AllocationModule.restockAndReallocate(new
AllocationModule.MyQueue(), statusModule);
}
break;
case 4:
AllocationModule.simulateDeliveryCompletion(statusModule);
break;
case 5:
AllocationModule.showFleet();
break;
case 6:
AllocationModule.showInventory();
break;
case 7:
running = false;
System.out.println("========================================");
System.out.println(" System Shutdown - Stay Safe!");
System.out.println("========================================");
break;
default:
System.out.println("✗ Invalid option");
}
}
}
}