using System;
using System.Collections.Generic;
using System.Text;

namespace TaskB
{
    class Book : IComparable
    {
        private string title;
        private string isbn;
        private Person author;

        public Book(string title, string isbn)
        {
            this.title = title;
            this.isbn = isbn;
            author = new Person("unknown");
        }

        public Book(string title, string isbn, Person author)
        {
            this.title = title;
            this.isbn = isbn;
            this.author = author;
        }
        public string Title
        {
            get { return title; }
            set { title = value; }
        }
        public string ISBN
        {
            get { return isbn; }
            set { isbn = value; }
        }

        public Person Author
        {
            get { return author; }
            set { author = value; }
        }

        public override string ToString()
        {
            string title = this.Title;
            Person author = this.Author;
            string name = author.Name;
            string age = author.Age.ToString();
            return "Title: " + title + "\n" + "ISBN: " + isbn + "\n" + "Author: " + name + "\n" + "Author's Age: " + age + "\n";
        }

        public int CompareTo(Object obj)
        {
            Book other = (Book)obj;
            return ISBN.CompareTo(other.ISBN);
        }
    }
}
