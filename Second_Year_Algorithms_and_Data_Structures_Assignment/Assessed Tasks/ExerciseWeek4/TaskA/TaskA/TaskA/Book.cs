using System;
using System.Collections.Generic;
using System.Text;

namespace TaskA
{
    class Book : IComparable
    {
        private string title;
        private string isbn;
        private string author;

        public Book(string isbn, string title, string author)
        {
            this.isbn = isbn;
            this.title = title;
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

        public string Author
        {
            get { return author; }
            set { author = value; }
        }

        public override string ToString()
        {
            return Title + " by " + Author + " ISBN: " + ISBN;
        }

        public int CompareTo(Object obj)
        {
            Book other = (Book)obj;
            return Title.CompareTo(other.Title);
        }
    }
}
