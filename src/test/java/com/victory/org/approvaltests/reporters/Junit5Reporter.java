package com.victory.org.approvaltests.reporters;

import com.spun.util.ObjectUtils;
import com.spun.util.io.FileUtils;
import org.approvaltests.reporters.ClipboardReporter;
import org.approvaltests.reporters.EnvironmentAwareReporter;
import org.approvaltests.reporters.GenericDiffReporter;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Junit5Reporter implements EnvironmentAwareReporter {
    @Override
    public boolean isWorkingInThisEnvironment(String forFile) {
        try
        {
            ObjectUtils.loadClass("org.junit.jupiter.api.Test");
        }
        catch (Throwable t)
        {
            return false;
        }
        return GenericDiffReporter.isFileExtensionValid(forFile, GenericDiffReporter.TEXT_FILE_EXTENSIONS);
    }

    @Override
    public void report(String received, String approved) {
        String aText = new File(approved).exists() ? FileUtils.readFile(approved) : "";
        String rText = FileUtils.readFile(received);
        String approveCommand = "To approve run : " + ClipboardReporter.getAcceptApprovalText(received, approved);
        System.out.println(approveCommand);
        assertEquals(aText, rText);
    }
}
