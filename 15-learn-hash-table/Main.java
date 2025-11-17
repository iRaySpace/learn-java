class Main {

    class Entry {

        String key;
        String value;
        Entry next;

        @Override
        public String toString() {
            return String.format("{key=%s, value=%s}", key, value);
        }

    }

    class HashTable {

        Entry[] buckets;

        HashTable(int size) {
            buckets = new Entry[size];
        }

        void put(String key, String value) {
            final Entry newEntry = new Entry();
            newEntry.key = key;
            newEntry.value = value;

            final int hashedIdx = getHashedIdx(key);
            System.out.println("hashedIdx: " + hashedIdx);

            // #1: First element
            if (buckets[hashedIdx] == null) {
                buckets[hashedIdx] = newEntry;
            } else {
                // #2: Second or more element
                Entry entryIterator = buckets[hashedIdx];
                while (entryIterator != null) {
                    if (entryIterator.key.equals(key)) {
                        entryIterator.value = value;
                        return;
                    }
                    if (entryIterator.next == null) break;
                    entryIterator = entryIterator.next;
                }
                entryIterator.next = newEntry;
            }

        }

        String get(String key) {
            final int hashedIdx = getHashedIdx(key);
            Entry bucket = buckets[hashedIdx];
            while (bucket != null) {
                if (bucket.key.equals(key)) {
                    return bucket.value;
                }
                bucket = bucket.next;
            }
            return null;
        }

        boolean containsKey(String key) {
            final int hashedIdx = getHashedIdx(key);
            Entry bucket = buckets[hashedIdx];
            while (bucket != null) {
                if (bucket.key.equals(key)) {
                    return true;
                }
                bucket = bucket.next;
            }
            return false;
        }

        void remove(String key) {
            final int hashedIdx = getHashedIdx(key);
            Entry bucket = buckets[hashedIdx];
            
            // #1: First element of the bucket
            if (bucket != null && bucket.key.equals(key)) {
                buckets[hashedIdx] = bucket.next;
                return;
            }

            // #2: Inside element of the bucket
            while (bucket.next != null) {
                final Entry nextBucket = bucket.next;
                if (nextBucket.key.equals(key)) {
                    bucket.next = nextBucket.next;
                    return;
                }
                bucket = bucket.next;
            }
        }

        private int getHashedIdx(String key) {
            return (key.hashCode() & Integer.MAX_VALUE) % buckets.length;
        }

    }

    void main() {
        final HashTable hashTable = new HashTable(8);
        hashTable.put("id", "69146e8c-ac4d-4ce8-a586-a66501b5171a"); // hashedIdx = 3
        hashTable.put("name", "Ivan Ray"); // hashedIdx = 3
        hashTable.put("abcde", "fghijkl"); // hashedIdx = 3

        System.out.println(hashTable.get("id"));
        System.out.println(hashTable.get("name"));    
        System.out.println(hashTable.containsKey("id"));
        
        hashTable.put("name", "Altomera");
        System.out.println(hashTable.get("name"));

        hashTable.remove("name");
        // hashTable.remove("abcde");
        System.out.println(hashTable.containsKey("name"));
        System.out.println(hashTable.containsKey("abcde"));
    }

}
