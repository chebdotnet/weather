package net.chebdotnet.weather.ip;

import reactor.core.publisher.Mono;

public interface IpResolverService {

    Mono<String> resolve();
}
