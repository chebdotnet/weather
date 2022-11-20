package net.chebdotnet.weather.ip;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@Profile("local")
@RequiredArgsConstructor
class LocalIpResolverService implements IpResolverService {

    private final AmazonAwsService amazonAwsService;

    @Override
    public Mono<String> resolve() {
        return amazonAwsService.getPublicIp();
    }

}
