using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace TaskA
{
    public partial class Form1 : Form
    {
        private NameQueue newQueue;
        public Form1()
        {
            int queueSize = 10;
            newQueue = new NameQueue(queueSize);
            InitializeComponent();
        }

        private void addButton_Click(object sender, EventArgs e)
        {
            if (!(newQueue.IsFull()))
            {
                newQueue.Enqueue(nameTextBox.Text);
                outputLabel.Text = nameTextBox.Text + " added.";
                outputLabel.Visible = true;
                countLabel.Text = "Total entry count: " + newQueue.TotalItems().ToString();
                nameTextBox.Text = "";
                nameTextBox.Focus();
            }
            
        }

        private void removeButton_Click(object sender, EventArgs e)
        {
            if (!(newQueue.IsEmpty()))
            {
                outputLabel.Text = newQueue.Dequeue() + " removed.";
                outputLabel.Visible = true;
                countLabel.Text = "Total entry count: " + newQueue.TotalItems().ToString();
            }
        }

        private void displayButton_Click(object sender, EventArgs e)
        {
            if (!(newQueue.IsEmpty()))
            {
                outputLabel.Text = newQueue.Peek();
                outputLabel.Visible = true;
            }
        }
    }
}
