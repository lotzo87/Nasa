    package mx.com.idmexico.vvazquez.nasa.Model;
    import com.google.gson.annotations.SerializedName;

    //@Generated("org.jsonschema2pojo")
    public class APOD {

        @SerializedName("copyright")
        private String copyright;
        @SerializedName("date")
        private String date;
        @SerializedName("explanation")
        private String explanation;
        @SerializedName("hdurl")
        private String hdurl;
        @SerializedName("media_type")
        private String mediaType;
        @SerializedName("service_version")
        private String serviceVersion;
        @SerializedName("title")
        private String title;
        @SerializedName("url")
        private String url;

        /**
         *
         * @return
         * The copyright
         */
        public String getCopyright() {
            return copyright;
        }

        /**
         *
         * @param copyright
         * The copyright
         */
        public void setCopyright(String copyright) {
            this.copyright = copyright;
        }

        /**
         *
         * @return
         * The date
         */
        public String getDate() {
            return date;
        }

        /**
         *
         * @param date
         * The date
         */
        public void setDate(String date) {
            this.date = date;
        }

        /**
         *
         * @return
         * The explanation
         */
        public String getExplanation() {
            return explanation;
        }

        /**
         *
         * @param explanation
         * The explanation
         */
        public void setExplanation(String explanation) {
            this.explanation = explanation;
        }

        /**
         *
         * @return
         * The hdurl
         */
        public String getHdurl() {
            return hdurl;
        }

        /**
         *
         * @param hdurl
         * The hdurl
         */
        public void setHdurl(String hdurl) {
            this.hdurl = hdurl;
        }

        /**
         *
         * @return
         * The mediaType
         */
        public String getMediaType() {
            return mediaType;
        }

        /**
         *
         * @param mediaType
         * The media_type
         */
        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        /**
         *
         * @return
         * The serviceVersion
         */
        public String getServiceVersion() {
            return serviceVersion;
        }

        /**
         *
         * @param serviceVersion
         * The service_version
         */
        public void setServiceVersion(String serviceVersion) {
            this.serviceVersion = serviceVersion;
        }

        /**
         *
         * @return
         * The title
         */
        public String getTitle() {
            return title;
        }

        /**
         *
         * @param title
         * The title
         */
        public void setTitle(String title) {
            this.title = title;
        }

        /**
         *
         * @return
         * The url
         */
        public String getUrl() {
            return url;
        }

        /**
         *
         * @param url
         * The url
         */
        public void setUrl(String url) {
            this.url = url;
        }

    }

