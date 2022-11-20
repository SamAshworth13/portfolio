using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TaskB
{
    class Request : IComparable
    {
        private int id;
        private string start, end;

        public Request(int id, string start, string end)
        {
            this.id = id;
            this.start = start;
            this.end = end;
        }

        public int ID
        {
            get { return id; }
            set { id = value; }
        }

        public string Start
        {
            get { return start; }
            set { start = value; }
        }

        public string End
        {
            get { return end; }
            set { end = value; }
        }

        public int CompareTo(object obj)
        {
            Request other = (Request)obj;
            return end.CompareTo(other.end);
        }

        
    }
}
