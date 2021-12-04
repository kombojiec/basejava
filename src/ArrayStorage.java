import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume resume) {
        if (size < storage.length) {
            storage[size++] = resume;
        } else {
            System.out.println("No empty space in storage for new resume");
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        System.out.printf("There is no resume with uuid = %s\n", uuid);
        return null;
    }

    void delete(String uuid) {
        int position = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                position = i;
                break;
            }
        }
        if (position >= 0) {
            System.arraycopy(storage, position + 1, storage, position, storage.length - 1 - position);
            --size;
        }
        if (position < 0) {
            System.out.printf("There is no resume with uuid = %s\n", uuid);
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    int size() {
        return size;
    }
}
