package net.chebdotnet.weather.ip;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

@Slf4j
@Service
@Profile("!local")
@RequiredArgsConstructor
class RemoteIpResolverService implements IpResolverService {

    @Override
    public Mono<String> resolve() {
        return Mono.just(getAddress());
    }

    private String getAddress() {
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface
                    .getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = (NetworkInterface) networkInterfaces
                        .nextElement();
                Enumeration<InetAddress> nias = ni.getInetAddresses();
                while (nias.hasMoreElements()) {
                    InetAddress ia = (InetAddress) nias.nextElement();
                    if (!ia.isLinkLocalAddress() && !ia.isLoopbackAddress() && ia instanceof Inet4Address) {
                        log.info("Got ip {} from request", ia.getHostAddress());
                        return ia.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            log.error("unable to get current IP " + e.getMessage(), e);
        }
        return null;
    }


}
