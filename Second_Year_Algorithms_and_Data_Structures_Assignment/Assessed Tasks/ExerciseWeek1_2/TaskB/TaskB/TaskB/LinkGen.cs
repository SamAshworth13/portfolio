using System;
using System.Collections.Generic;
using System.Text;

namespace TaskB
{
    class LinkGen<T>
    {
        private T data;
        private LinkGen<T> next;

        public LinkGen(T item) //constructor with an item
        {
            this.data = item;
            next = null;
        }
        public LinkGen(T item, LinkGen<T> list) //constructor with item and list
        {
            this.data = item;
            this.next = list;
        }

        public T Data //property for data
        {
            set { this.data = value; }
            get { return this.data; }
        }

        public LinkGen<T> Next //property for next
        {
            set { this.next = value; }
            get { return this.next; }
        }

    }
}
