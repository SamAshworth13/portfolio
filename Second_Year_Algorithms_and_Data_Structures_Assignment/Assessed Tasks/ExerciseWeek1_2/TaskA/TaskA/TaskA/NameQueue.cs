using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TaskA
{
    class NameQueue
    {
        private readonly int maxsize = 10;
        private string[] store;
        private int head = 0;
        private int tail = 0;
        private int numItems;

        public NameQueue(int size)
        {
            maxsize = size;
            store = new string[maxsize];
        }

        public NameQueue()
        {
            store = new string[maxsize];
        }

        public void Enqueue(string name)
        {
            store[tail] = name;
            numItems++;
            if (++tail == maxsize)
            {
                tail = 0;
            }

        }

        public string Dequeue()
        {
            string name = store[head];
            numItems--;
            if (++head == maxsize)
            {
                head = 0;
            }
            return name;
        }

        public string Peek()
        {
            return store[head];
        }

        public bool IsEmpty()
        {
            return numItems == 0;
        }

        public bool IsFull()
        {
            return numItems == maxsize;
        }

        public int TotalItems()
        {
            return numItems;
        }

    }
}
