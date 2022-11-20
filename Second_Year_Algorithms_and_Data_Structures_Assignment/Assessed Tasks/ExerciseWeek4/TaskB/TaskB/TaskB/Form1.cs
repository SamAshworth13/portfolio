using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace TaskB
{
    public partial class Form1 : Form
    {
        Request[] reqArray = new Request[100];
        public Form1()
        {
            InitializeComponent();
        }

        private void submitButton_Click(object sender, EventArgs e)
        {
            int id = Convert.ToInt32(idTextBox.Text);
            string start = startTextBox.Text;
            string end = finishTextBox.Text;
            bool added = false;
            Request req = new Request(id, start, end);
            for (int i = 0; i < reqArray.Length; i++)
            {
                if (!added)
                {
                    if (reqArray[i] != null)
                    {
                        if (req.CompareTo(reqArray[i]) < 0)
                        {
                            InsertItem(req, ref reqArray, i);
                            added = true;
                        }
                    }
                    else
                    {
                        reqArray[i] = req;
                        added = true;
                    }
                
                }
            }
        }

        private void displayButton_Click(object sender, EventArgs e)
        {
            displayListBox.Items.Clear();
            if (reqArray[0] != null)
            {
                Request[] chosenArray = new Request[100];
                chosenArray[0] = reqArray[0];
                string prevEnd = chosenArray[0].End;
                int nextEmpty = 1;
                for (int i = 1; i < reqArray.Length; i++)
                {
                    if (reqArray[i] != null)
                    {
                        if (prevEnd.CompareTo(reqArray[i].Start) <= 0)
                        {
                            chosenArray[nextEmpty] = reqArray[i];
                            prevEnd = reqArray[i].End;
                            nextEmpty++;
                        }
                    }
                }

                foreach (Request item in chosenArray)
                {
                    if (item != null)
                    {
                        displayListBox.Items.Add("ID: " + item.ID + " Start: " + item.Start + " End: " + item.End);
                    }
                }
                     
            }
        }

        private void InsertItem<T>(T item, ref T[] array, int i)
        {
            // check if item is null (stopping condition)
            if (item != null)
            {
                //store the current value of array[i]
                T temp = array[i];
                //set array[i] to be the new value
                array[i] = item;
                //recursive call with temp, to move it one further on in array
                InsertItem(temp, ref array, i + 1);
            }
        }
    }
}
