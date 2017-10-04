/*
 * MIT License
 *
 * Copyright (c) 2016 M.-Leander Reimer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package everything.as.code;

import fish.payara.micro.BootstrapException;
import fish.payara.micro.PayaraMicro;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The main application that fires up Payara Micro.
 */
public class BookApplication {

    /**
     * Pass all command line arguments to Payara micro server. Do not
     * forget to set PORT environment variable.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) throws BootstrapException, IOException {
        //disable DNS caching
        java.security.Security.setProperty("networkaddress.cache.ttl", "0");

        // get HTTP port base
        Optional<String> port = Optional.ofNullable(System.getenv("PORT"));

        PayaraMicro.getInstance()
                .setInstanceName("BookService-" + UUID.randomUUID().toString())
                .setHttpPort(Integer.valueOf(port.orElse("18080")))
                .setHttpAutoBind(true)
                .setAutoBindRange(20)
                .setPrintLogo(false)
                .setNoCluster(true)
                .setDeploymentDir(getDeploymentDir())
                .bootStrap();
    }

    private static File getDeploymentDir() throws IOException {
        Path start = new File(".").getCanonicalFile().toPath();

        List<Path> paths = Files.find(start, Integer.MAX_VALUE,
                (filePath, fileAttr) -> filePath.toFile().getName().endsWith(".war") && fileAttr.isRegularFile())
                .collect(Collectors.toList());

        if (paths.isEmpty()) {
            // try from parent directory
            start = new File(".").getCanonicalFile().getParentFile().toPath();

            paths = Files.find(start, Integer.MAX_VALUE,
                    (filePath, fileAttr) -> filePath.toFile().getName().endsWith(".war") && fileAttr.isRegularFile())
                    .collect(Collectors.toList());
        }

        return paths.get(0).toFile().getParentFile();
    }
}
