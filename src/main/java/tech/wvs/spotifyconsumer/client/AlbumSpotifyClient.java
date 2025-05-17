package tech.wvs.spotifyconsumer.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "AuthSpotifyClient",
        url = "https://api.spotify.com")
public interface AlbumSpotifyClient {

    @GetMapping(path = "/v1/browse/new-releases")
    AlbumResponse getReleases(@RequestHeader("Authorization") String authorization);
}
