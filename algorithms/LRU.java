import java.util.Iterator;
import java.util.LinkedHashSet;

class LRU
{
    // Method to find page faults using indexes
    static int pageFaults(int pages[], int capacity)
    {
        // If the user gives capacity of memory equal to zero, returning 0 since there aren't going to be any faults
        if (capacity == 0)
            return 0;

        // LinkedHashSet is implemented as a hash table with a linked list running through it,
        // so it provides the order of insertion. The time complexity of basic methods is O(1).
        // That way the time complexity for adding and removing pages is O(1).
        LinkedHashSet<Integer> memory = new LinkedHashSet<>(capacity);

        int faults = 0; // Initializing the page faults

        // Iterating through every page
        for (int page : pages)
        {
            // If this page is in the set, we remove it and adding it at the tail set since it's implemented
            // as a linked list to maintain the LRU logic
            if (memory.contains(page))
            {
                memory.remove(page); // Removes the page
                memory.add(page); // Adds the new page into the hash set
            }

            // Else adding the new page at the tail set since it's implemented as a linked list to maintain
            // the LRU logic
            else
            {
                // If it reached its capacity, removing the last recently used page first(first element of the set) and
                // then and adding the new page
                if (memory.size() == capacity)
                {
                    Iterator iterator = memory.iterator(); // Using and iterator just to get the first element of the set in O(1)
                    int firstPageIndex = (int)iterator.next();
                    memory.remove(firstPageIndex); // Removes he RLU page
                }

                memory.add(page); // Adds the new page into the hash set
                faults++; // Increasing page faults
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
