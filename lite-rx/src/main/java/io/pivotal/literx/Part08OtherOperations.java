package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Learn how to use various other operators.
 *
 * @author Sebastien Deleuze
 */
public class Part08OtherOperations {

//========================================================================================

	Flux<User> userFluxFromStringFlux(Flux<String> usernameFlux, Flux<String> firstnameFlux, Flux<String> lastnameFlux) {
		return Flux.zip(usernameFlux, firstnameFlux, lastnameFlux)
			.map(triple -> new User(triple.getT1(), triple.getT2(), triple.getT3()));
	}

//========================================================================================

	Mono<User> useFastestMono(Mono<User> mono1, Mono<User> mono2) {
		return Mono.firstWithValue(mono1, mono2);
	}

//========================================================================================

	Flux<User> useFastestFlux(Flux<User> flux1, Flux<User> flux2) {
		return Flux.firstWithValue(flux1, flux2);
	}

//========================================================================================

	Mono<Void> fluxCompletion(Flux<User> flux) {
		return flux.then();
	}

//========================================================================================

	Mono<User> nullAwareUserToMono(User user) {
		return Mono.justOrEmpty(user);
	}

//========================================================================================

	Mono<User> emptyToSkyler(Mono<User> mono) {
		return mono.defaultIfEmpty(User.SKYLER);
	}

//========================================================================================

	Mono<List<User>> fluxCollection(Flux<User> flux) {
		return flux.collectList();
	}

}
