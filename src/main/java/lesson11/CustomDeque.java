package lesson11;


public interface CustomDeque {
    void addFirst(int i); // добавление в начало
    int getFirst() throws IndexOutOfBoundsException; // запрос первого элемента без удаления
    int removeFirst() throws IndexOutOfBoundsException; // получение 1 элемента и удаление

    void addLast(int i); // добавление в конец
    int getLast() throws IndexOutOfBoundsException; // получение последнего элемента без удаления
    int removeLast() throws IndexOutOfBoundsException; // получение последнего с удалением

    int size();
}

/*
    method         firstElementIndex  size     state
1   addLast(10)    0                  1        {10}
2   addLast(15)    0                  2        {10, 15}
3   removeFirst()  1                  1        {15}
4   addLast(47)    1                  2        {15,47}
5   addFirst(53)   0                  3        {53,15,47}
6   addFirst(8)    4 - 1 = 3          4        {8,53,15,47}

1|  10    |       |         |          |
2|  10    |  15   |         |          |
3|        |  15   |         |          |
4|        |  15   |  47     |          |
5|  53    |  15   |  47     |          |
6|  53    |  15   |  47     |    8     |

7   addLast(24)   0                    5       {8,53,15,47,24}
8   addFirst(5)   7                    6       {5,8,53,15,47,24}

7|  8     |  53   |  15     |    47    |   24   |       |         |          |
8|  8     |  53   |  15     |    47    |   24   |       |         |     5    |
 */
