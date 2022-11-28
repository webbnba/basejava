/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for(int i = 0; i <= size; i++) {
                storage[i] = null;
                size--;
        }
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
        if(size == storage.length) {
            Resume[] newArray = new Resume[storage.length * 2];
            for(int i = 0; i < newArray.length; i++) {
                newArray[i] = storage[i];
            }
            storage = newArray;
        }

    }

    Resume get(String uuid) {
        Resume resume = null;
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                resume = storage[i];
            }
        }
        return resume;
    }

    void delete(String uuid) {
        for(int i = 0; i < size; i++) {
            if(storage[i].toString().equals(uuid)) {
               for(int k = i; k < size - 1; k++) {
                   storage[k] = storage[k + 1];
               }
               size--;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] newArray = new Resume[size];
        for(int i = 0; i < size; i++) {
            if(storage[i] != null) {
                newArray[i] = storage[i];
            }
        }

        return newArray;
    }

    int size() {
        return size;
    }
}
