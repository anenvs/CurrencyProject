package ru.sidorov.currencyproject.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sidorov.currencyproject.dto.HelloResponseDto;

@FeignClient(name = "hello-api-client")
public interface HelloApiClient {
        @GetMapping("${spring.cloud.openfeign.client.config.hello-api-client.path}")
    HelloResponseDto hello(@RequestParam String name);
}
