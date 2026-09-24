class Solution {
    public ListNode mergeKLists(ListNode[] lists) {

        PriorityQueue<ListNode> pq = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        // Har linked list ka first node heap mein daalo
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!pq.isEmpty()) {

            // Sabse chhota node nikalo
            ListNode smallest = pq.poll();

            // Answer mein add karo
            curr.next = smallest;
            curr = curr.next;

            // Agar is node ke baad koi node hai,
            // toh usko heap mein daalo
            if (smallest.next != null) {
                pq.offer(smallest.next);
            }
        }

        return dummy.next;
    }
}
