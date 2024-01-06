import java.util.ArrayList;
public class binarySearchClass
{
    public static int binarySearchonArray(ArrayList<String> arr, String key)
    {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            String midValue = arr.get(mid);

            int comparisonResult = key.compareTo(midValue);

            if (comparisonResult == 0)
            {
                return mid; // Key found, return its index
            } else if (comparisonResult < 0)
            {
                high = mid - 1;
            } else
            {
                low = mid + 1;
            }
        }

        return -1; // Key not found
    }
}