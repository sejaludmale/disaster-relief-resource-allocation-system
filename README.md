#  Disaster Relief Resource Allocation System

##  Project Description
This is a Java-based Disaster Relief Resource Allocation System developed using Data Structures and Algorithms (DSA).  
It helps manage disaster-affected areas and efficiently allocate resources based on priority and availability.



##  Features
- Add and manage disaster-affected areas
- Priority-based resource allocation
- Vehicle tracking for resource delivery
- Area status monitoring system
- Efficient data handling using custom data structures (Queue, Heap, Hash Table)



##  Tech Stack
- Java (Core Java)
- Object-Oriented Programming (OOP)
- Data Structures (Queue, Heap, Hashing)
- Eclipse IDE



##  Project Structure
- `src/` → Source code files
- `bin/` → Compiled files (ignored in GitHub)
- `Main.java` → Entry point of application



## How to Run
1. Clone the repository
2. Open project in Eclipse IDE
3. Import as existing Java project
4. Run `Main.java`



##  Output 
```text
========================================
 DISASTER RELIEF MANAGEMENT SYSTEM
========================================

============================================================
|| ? AREA INPUT & ASSESSMENT MODULE
============================================================
Enter number of affected areas: 3

------------------------------------------------------------
--- Enter details for Area 1 ---
Area name: Mumbai
Disaster Type (Flood/Earthquake/Fire/etc): Flood
Urgency (1=High, 2=Medium, 3=Low): 1
Distance from warehouse (km): 10
Population affected: 200
Number of casualties: 8
Medical emergency? (yes/no): yes
Resources needed (Medical Kits/Blankets/Food Packets/Water Bottles): Medical Kits
Road accessible? (yes/no): yes
Contact person: Raj Patel
Contact number: 9876543210           
------------------------------------------------------------
? Area registered | Severity: Medium | ETA: 30 min

------------------------------------------------------------
--- Enter details for Area 2 ---
Area name: Pune
Disaster Type (Flood/Earthquake/Fire/etc): Fire
Urgency (1=High, 2=Medium, 3=Low): 2
Distance from warehouse (km): 20
Population affected: 150
Number of casualties: 3
Medical emergency? (yes/no): no
Resources needed (Medical Kits/Blankets/Food Packets/Water Bottles): Blankets
Road accessible? (yes/no): yes
Contact person: Amit Shah
Contact number: 9123456780
------------------------------------------------------------
? Area registered | Severity: Low | ETA: 60 min

------------------------------------------------------------
--- Enter details for Area 3 ---
Area name: Nashik
Disaster Type (Flood/Earthquake/Fire/etc): Earthquake
Urgency (1=High, 2=Medium, 3=Low): 1
Distance from warehouse (km): 5
Population affected: 300
Number of casualties: 15
Medical emergency? (yes/no): yes
Resources needed (Medical Kits/Blankets/Food Packets/Water Bottles): Food Packets
Road accessible? (yes/no): no
Contact person: Sneha Patil
Contact number: 9988776655
------------------------------------------------------------
? Area registered | Severity: Medium | ETA: 45 min
============================================================
|| Total Areas Registered: 3
============================================================


================================================================================
|| ? PRIORITY DISPATCH ORDER (Max-Heap Result)                                 
================================================================================
Order Area            Severity   Dist(km)   Urgency      Resource            
--------------------------------------------------------------------------------
1     Nashik          Medium     5          1            Food Packets        
2     Mumbai          Medium     10         1            Medical Kits        
3     Pune            Low        20         2            Blankets            
================================================================================


============================================================
|| ? RESOURCE ALLOCATION DECISIONS                         
============================================================

--- DISPATCHED ---
? Nashik          | Vehicle: AMB-01     | ETA: 45 min
 Resources: 30 Food Packets
 Contact: Sneha Patil (9988776655)

--- DISPATCHED ---
? Mumbai          | Vehicle: TRK-01     | ETA: 30 min
 Resources: 20 Medical Kits
 Contact: Raj Patel (9876543210)

--- DISPATCHED ---
? Pune            | Vehicle: TRK-02     | ETA: 60 min
 Resources: 15 Blankets
 Contact: Amit Shah (9123456780)
============================================================
|| TOTALS: Dispatched: 3 | Pending: 0
============================================================


============================================================
|| ? DELIVERY STATUS SUMMARY (MyHashTable)                  
============================================================
AREA NAME                      STATUS                        
------------------------------------------------------------
Mumbai                         Dispatched - En Route         
Nashik                         Dispatched - En Route         
Pune                           Dispatched - En Route         
============================================================

=== RESPONSE STATISTICS ===
Total Areas: 3
Dispatched: 3 (100%)
Pending: 0 (0%)
===========================
=== VEHICLE FLEET STATUS ===
 AMB-01 (Ambulance) - Assigned to Nashik
 TRK-01 (Truck) - Assigned to Mumbai
 TRK-02 (Truck) - Assigned to Pune
 HEL-01 (Helicopter) - Available
============================
=== INVENTORY STATUS ===
 [?] Medical Kits        :  80 units
 [?] Blankets            :  65 units
 [?] Food Packets        : 120 units
 [?] Water Bottles       : 120 units
========================
=== OPERATIONS MENU ===
1. View Status
2. View Statistics
3. Restock & Reallocate PENDING
4. Simulate Delivery Completion
5. View Fleet
6. View Inventory
7. Exit
Select: 1

============================================================
|| ? DELIVERY STATUS SUMMARY (MyHashTable)                  
============================================================
AREA NAME                      STATUS                        
------------------------------------------------------------
Mumbai                         Dispatched - En Route         
Nashik                         Dispatched - En Route         
Pune                           Dispatched - En Route         
============================================================

=== OPERATIONS MENU ===
1. View Status
2. View Statistics
3. Restock & Reallocate PENDING
4. Simulate Delivery Completion
5. View Fleet
6. View Inventory
7. Exit
Select: 2
=== RESPONSE STATISTICS ===
Total Areas: 3
Dispatched: 3 (100%)
Pending: 0 (0%)
===========================
=== OPERATIONS MENU ===
1. View Status
2. View Statistics
3. Restock & Reallocate PENDING
4. Simulate Delivery Completion
5. View Fleet
6. View Inventory
7. Exit
Select: 3
? No requests are pending. Use option 6 to check inventory.
=== RESTOCKING MODULE ===
Current Stock:
 Medical Kits: 80
 Blankets: 65
 Food Packets: 120
 Water Bottles: 120
Resource type to restock: 4
Quantity to add: 5
? Restocked: 4 (+5)
 New Stock: 5
Fulfilled: 0 areas
=== OPERATIONS MENU ===
1. View Status
2. View Statistics
3. Restock & Reallocate PENDING
4. Simulate Delivery Completion
5. View Fleet
6. View Inventory
7. Exit
Select: 6
=== INVENTORY STATUS ===
 [?] Medical Kits        :  80 units
 [?] Blankets            :  65 units
 [?] 4                   :   5 units
 [?] Food Packets        : 120 units
 [?] Water Bottles       : 120 units
========================
=== OPERATIONS MENU ===
1. View Status
2. View Statistics
3. Restock & Reallocate PENDING
4. Simulate Delivery Completion
5. View Fleet
6. View Inventory
7. Exit
Select: 7
========================================
 System Shutdown - Stay Safe!
========================================

---

## Author
- Sejal Udmale


