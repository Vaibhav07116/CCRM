package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Handles the creation of timestamped backups of exported data.
 * Demonstrates advanced NIO.2 features like walking file trees and recursion.
 */
public class BackupService {

    private final Path sourceDir = Paths.get("exports");
    private final Path backupRootDir = Paths.get("backups");

    /**
     * Creates a backup of the 'exports' directory.
     * The backup is a new directory inside 'backups' with a timestamped name.
     */
    public void createBackup() {
        if (!Files.exists(sourceDir)) {
            System.err.println("Backup failed: Source directory 'exports' does not exist. Please export data first.");
            return;
        }

        // 1. Create a timestamped name for our backup folder.
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        Path backupTargetDir = backupRootDir.resolve("backup_" + timestamp);

        try {
            // 2. Create the main backups directory and the specific timestamped directory.
            Files.createDirectories(backupTargetDir);

            // 3. Walk the source directory and copy each file to the target.
            // This is a common and powerful pattern for directory copying.
            try (var paths = Files.walk(sourceDir)) {
                paths.forEach(sourcePath -> {
                    try {
                        if (Files.isRegularFile(sourcePath)) {
                            Path destinationPath = backupTargetDir.resolve(sourceDir.relativize(sourcePath));
                            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                        }
                    } catch (IOException e) {
                        System.err.println("Failed to copy file: " + sourcePath + " - " + e.getMessage());
                    }
                });
            }

            System.out.println("Backup created successfully at: " + backupTargetDir.toAbsolutePath());

            // 4. Demonstrate the recursive size calculation as required.
            long backupSize = calculateDirectorySize(backupTargetDir);
            System.out.printf("Total size of the new backup is: %.2f KB%n", backupSize / 1024.0);

        } catch (IOException e) {
            System.err.println("An error occurred while creating the backup: " + e.getMessage());
        }
    }

    /**
     * Recursively calculates the total size of a directory.
     * This method fulfills the requirement for a recursive utility.
     *
     * @param directory The path to the directory.
     * @return The total size in bytes.
     */
    private long calculateDirectorySize(Path directory) throws IOException {
        // AtomicLong is used here because we are modifying it from within a lambda expression,
        // which requires the variable to be "effectively final".
        final AtomicLong size = new AtomicLong(0);

        // Files.walkFileTree is a classic implementation of the Visitor design pattern.
        // It "visits" every file and directory in the tree.
        Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                // For each file we visit, add its size to our total.
                size.addAndGet(attrs.size());
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) {
                // If we can't access a file, just print a warning and continue.
                System.err.println("Warning: Could not access file to calculate size: " + file);
                return FileVisitResult.CONTINUE;
            }
        });

        return size.get();
    }
}
