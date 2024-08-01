package com.ir.spring.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RestController
@Slf4j
public class TestController {

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final String outputPath = "output.txt";

    @GetMapping("/doPrint")
    public String printSync(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < input.length(); j++) {
                sb.append(input.charAt(j));
                System.out.println(input.charAt(j));
            }
        }

        return sb.toString();
    }

    @GetMapping("/dontPrint")
    public String withoutPrint(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < input.length(); j++) {
                sb.append(input.charAt(j));
            }
        }

        return sb.toString();
    }

    @GetMapping("/doPrintAsync")
    public String printAsync(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < input.length(); j++) {
                sb.append(input.charAt(j));
                char c = input.charAt(j);
                CompletableFuture.runAsync(() ->
                        System.out.println(c), executorService);
            }
        }

        return sb.toString();
    }

    @GetMapping("/log")
    public String log(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < input.length(); j++) {
                sb.append(input.charAt(j));
                log.info(String.valueOf(input.charAt(j)));
            }
        }

        return sb.toString();
    }

    @GetMapping("/logAsync")
    public String logAsync(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < input.length(); j++) {
                sb.append(input.charAt(j));
                char c = input.charAt(j);
                CompletableFuture.runAsync(() ->
                        log.info(String.valueOf(c)), executorService);
            }
        }

        return sb.toString();
    }


//    @GetMapping("/doPrintWithChannel")
//    public String recordWithChannel(String input){
//        StringBuilder sb = new StringBuilder();
//        ByteBuffer buffer = ByteBuffer.allocate(1024);
//
//        try (FileOutputStream fos = new FileOutputStream("channel-log.txt");
//             FileChannel channel = fos.getChannel()) {
//
//            for (int i = 0; i < 100; i++) {
//                for (int j = 0; j < input.length(); j++) {
//                    sb.append(input.charAt(j));
//                    buffer.put(sb.toString().getBytes());
//                    buffer.flip();
//                    channel.write(buffer);
//                    buffer.clear();
//                }
//            }
//            channel.force(true);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return sb.toString();
//    }

//    @GetMapping("/doPrintWithChannel")
//    public String recordWithChannel(String input) throws Exception {
//        StringBuilder sb = new StringBuilder();
//        Path path = Path.of("channel-log.txt");
//
//        try (AsynchronousFileChannel asyncFileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
//            for (int i = 0; i < 100; i++) {
//                for (int j = 0; j < input.length(); j++) {
//                    sb.append(input.charAt(j));
//                }
//            }
//
//            ByteBuffer buffer = ByteBuffer.wrap(sb.toString().getBytes());
//            Future<Integer> result = asyncFileChannel.write(buffer, 0);
//
//            // 결과가 완료될 때까지 기다립니다.
//            while (!result.isDone()) {
//                // 비동기 작업을 기다리는 동안 다른 작업을 수행할 수 있습니다.
//            }
//        }
//
//        return sb.toString();
//    }

    @GetMapping("/doPrintWithStream")
    public String recordWithStream(String input) {
        StringBuilder sb = new StringBuilder();

        try (FileOutputStream fos = new FileOutputStream("stream-log.txt")) {

            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < input.length(); j++) {
                    sb.append(input.charAt(j));
                    fos.write(input.charAt(j));
                    fos.flush(); // Ensure data is written to disk
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
