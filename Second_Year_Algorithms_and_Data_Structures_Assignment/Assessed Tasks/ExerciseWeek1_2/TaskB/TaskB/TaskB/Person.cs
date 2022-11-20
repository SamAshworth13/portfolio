using System;
using System.Collections.Generic;
using System.Text;

namespace TaskB
{
    class Person : IComparable
    {
        private int age;
        private string name;

        public Person(string name) //constructor with one argument
        {
            this.name = name;
            age = 18; //default age
        }
        public Person(string name, int age)
        {
            this.name = name;
            this.age = age; 
        }

        public int Age
        {
            get { return age; }
            set { age = value; }
        }

        public string Name
        {
            get { return name; }
            set { name = value; }
        }


        public int CompareTo(Object obj) //implementation of CompareTo
        {					// 		for IComparable

            Person other = (Person)obj;
            return Name.CompareTo(other.Name);
        }

    }

}
