using System;
using System.ComponentModel.DataAnnotations;

namespace TodosDb
{
    public class Todo
    {
        [Key]
        public int Id { get; set; }
        public string Value { get; set; }

    }
}
