package id.ac.poliban.mi.ramlan.materialme_starter_052;


class Sport {
    private final String title;
    private final String info;
    private final int imageResource;

    public Sport(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }
        String getTitle() {
            return title;
        }
        String getInfo() {
            return info;
        }

        public int getImageResource() {
            return imageResource;
    }
}
