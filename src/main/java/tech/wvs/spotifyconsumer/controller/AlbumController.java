package tech.wvs.spotifyconsumer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.wvs.spotifyconsumer.client.*;

import java.util.List;

@RestController
@RequestMapping("/spotify/api")
public class AlbumController {

    private final AuthSpotifyClient spotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;


    public AlbumController(AuthSpotifyClient spotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.spotifyClient = spotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }

    @GetMapping(path = "/albums")
    public ResponseEntity<List<Album>> getAlbums() {
        var request = new LoginRequest(
                "client_credentials",
                "",
                ""
        );

        var token = spotifyClient.login(request).getAccessToken();

        var releases = albumSpotifyClient.getReleases("Bearer " + token);

        return ResponseEntity.ok(releases.getAlbums().getItems());
    }
}
