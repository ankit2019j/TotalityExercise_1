package ankit.com.totalityexercise;

import com.google.gson.annotations.SerializedName;

public class fact_sets {

        private String title;
        private String description;
        private String image;



        public fact_sets(String title, String description, String image) {
            this.title = title;
            this.description = description;
            this.image = image;
        }


        @SerializedName("title")
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @SerializedName("description")
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @SerializedName("imageHref")
        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }


