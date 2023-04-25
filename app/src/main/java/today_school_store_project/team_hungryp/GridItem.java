package today_school_store_project.team_hungryp;

import com.google.firebase.storage.StorageReference;

public class GridItem {
    StorageReference storageReference;
    String name;
    public GridItem(StorageReference storageReferencem, String name){
        this.storageReference = storageReferencem;
        this.name = name;
    }

    public StorageReference getStorageReference() {
        return storageReference;
    }

    public void setStorageReference(StorageReference storageReference) {
        this.storageReference = storageReference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
