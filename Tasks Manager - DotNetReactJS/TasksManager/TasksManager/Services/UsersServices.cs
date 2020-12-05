using System;
using System.Linq;
using TasksManager.DbAccess;
using TasksManager.Model;

namespace TasksManager.Services
{
    public class UsersServices : IUsersServices
    {
        private TodosDbContext context;
    

        public UsersServices (TodosDbContext dbContext)
        {
            this.context = dbContext;
        }

        public int Login (string email, string password)
        {
            User user = context.Users.FirstOrDefault(u => String.Equals(u.Email, email) && String.Equals(u.Password, password));

            if (user != null)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }

        public int Signup(string name, string email, string password)
        {
            User checkExisting = context.Users.FirstOrDefault(u => String.Equals(u.Email, email));
            
            if (checkExisting != null)
            {
                return 0;
            }

            User user = new User();
            user.Name = name;
            user.Email = email;
            user.Password = password;

            context.Users.Add(user);
            context.SaveChanges();

            return 1;

        }

    }
}
