import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
class FIFO
{
    // Method to find page faults using indexes
    static int pageFaults(int pages[], int capacity)
    {
        // If the user gives capacity of memory equal to zero, returning 0 since there aren't going to be any faults
        if (capacity == 0)
            return 0;

        // In order to have a First In First Out implementation, we use a queue (linked list) to store the pages.
        Queue<Integer> memory = new LinkedList<>() ;

        // Using a HashSet in order to check in O(1) if a page exists in the queue or not
        HashSet<Integer> pagesInFrames = new HashSet<>(capacity);

        int faults = 0; // Initializing the page faults

        // Iterating through every page
        for (int page : pages)
        {
            // If this page is not in the set (which means is not in the queue either) we add it, otherwise we ignore it
            if(!pagesInFrames.contains(page))
            {
                // If the queue is currently full, removing the first inserted page from the queue and also from
                // the hash set in order to add the new page.
                if (memory.size() == capacity)
                {
                    int firstPage = memory.poll(); // Pops the first page of the queue
                    pagesInFrames.remove(firstPage);  // Removes the first page from the hash set

                }

                memory.add(page); // Pushing the new page into the queue
                pagesInFrames.add(page); // Adding the new page into the hash set
                faults++; // Increasing page faults, since thew new page was not found
            }
        }
        return faults;
    }

    // Driver Method to test your algorithm with a simple example
    public static void main(String args[])
    {
        /*
         * This is an array that holds the reference string for all
         * page requests.
         */
        int pages[] = {5, 1, 0, 3, 2, 3, 0, 4, 2, 3, 0, 3, 5, 2};

        // This is the number of available page frames
        int memoryCapacity = 3;

        int faults = pageFaults(pages, memoryCapacity);
        System.out.println(faults);
    }
}
