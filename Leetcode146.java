class LRUCache {

    class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        // Dummy nodes
        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    // Add node just after head = most recently used
    private void addNode(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }

    // Remove node from linked list
    private void removeNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    public int get(int key) {

        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);

        // Accessed -> make it most recently used
        removeNode(node);
        addNode(node);

        return node.value;
    }

    public void put(int key, int value) {

        // Key already exists
        if (map.containsKey(key)) {
            Node node = map.get(key);

            node.value = value;

            removeNode(node);
            addNode(node);

            return;
        }

        // New node
        Node newNode = new Node(key, value);

        map.put(key, newNode);
        addNode(newNode);

        // Capacity exceeded
        if (map.size() > capacity) {

            // Least recently used = node before tail
            Node lru = tail.prev;

            removeNode(lru);
            map.remove(lru.key);
        }
    }
}
