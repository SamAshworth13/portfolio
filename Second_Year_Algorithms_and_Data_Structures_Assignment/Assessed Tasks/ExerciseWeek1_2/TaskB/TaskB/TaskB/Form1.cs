using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace TaskB
{
    public partial class Form1 : Form
    {
        LinkListGen<Book> bookList = new LinkListGen<Book>();
        public Form1()
        {
            InitializeComponent();
        }

        private void addButton_Click(object sender, EventArgs e)
        {
            Person newAuthor = new Person(nameTextBox.Text, Convert.ToInt32(ageTextBox.Text));
            Book newBook = new Book(titleTextBox.Text, isbnTextBox.Text, newAuthor);
            bookList.AddItem(newBook);
            displayLabel.Text = "Book added.";
            displayLabel.Visible = true;
        }

        private void displayButton_Click(object sender, EventArgs e)
        {
            displayLabel.Text = bookList.DisplayItems();
            displayLabel.Visible = true;
        }

        private void removeButton_Click(object sender, EventArgs e)
        {
            Book rmvBook = new Book("", removeTextBox.Text, new Person(""));
            bookList.RemoveItem(rmvBook);
            displayLabel.Text = "Book Removed.";
            displayLabel.Visible = true;
        }

        private void sortButton_Click(object sender, EventArgs e)
        {
            bookList.Sort();
        }
    }
}
