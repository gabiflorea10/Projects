using Assignment4DotNet.Model;
using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;

namespace Assignment4DotNet.DataLayer
{
    public class DataAccess
    {
        public List<RecommendationDTO> getRecommendationData(String caregiver)
        {
            List<RecommendationDTO> recommendations = new List<RecommendationDTO>();

            MySqlConnection mySql = new MySqlConnection("datasource=localhost;username=root;password=password");
            mySql.Open();

            if (mySql.State == System.Data.ConnectionState.Open)
            {
                MySqlCommand command;
                MySqlDataReader reader;
                String query = "select * from ds.recommendation where caregiver_username='" + caregiver + "'";
                command = new MySqlCommand(query, mySql);
                reader = command.ExecuteReader();


                while (reader.Read())
                {

                    RecommendationDTO recommendation = new RecommendationDTO();

                    recommendation.patientName = (String)reader.GetValue(2);
                    recommendation.description = ((String)reader.GetValue(3));

                    recommendations.Add(recommendation);

                }


            }

            mySql.Close();

            return recommendations;

        }
    }
}