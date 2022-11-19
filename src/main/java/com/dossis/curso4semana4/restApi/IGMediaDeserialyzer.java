package com.dossis.curso4semana4.restApi;

import com.dossis.curso4semana4.pojo.IGMediaItem;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class IGMediaDeserialyzer implements JsonDeserializer<IGMediaResponse> {
    @Override
    public IGMediaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        Gson gson = new Gson();
        IGMediaResponse IGMediaResponse = gson.fromJson(json, IGMediaResponse.class);
        JsonArray dataNodeArray = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_NODE_ARRAY);
        IGMediaResponse.setMediaItems(deserializarIGMedia(dataNodeArray));
        return IGMediaResponse;

    }

    private ArrayList<IGMediaItem> deserializarIGMedia(JsonArray dataNodeArray) {
        ArrayList<IGMediaItem> mediaItems = new ArrayList<>();
        for (int i = 0; i < dataNodeArray.size(); i++) {

            JsonObject IGResponseDataObject = dataNodeArray.get(i).getAsJsonObject();
            String id = IGResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String username = IGResponseDataObject.get(JsonKeys.USER_USER_NAME).getAsString();
            String media_url = IGResponseDataObject.get(JsonKeys.MEDIA_URL).getAsString();
            int media_likes = IGResponseDataObject.get(JsonKeys.MEDIA_LIKES).getAsInt();

            IGMediaItem currentPetProfile = new IGMediaItem();
            currentPetProfile.setId(id);
            currentPetProfile.setPetName(username);
            currentPetProfile.setUrlPetPic(media_url);
            currentPetProfile.setLikes(media_likes);

            mediaItems.add(currentPetProfile);

        }
        return mediaItems;
    }
}
