package com.google.refine.freebase.commands;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.refine.ProjectManager;
import com.google.refine.commands.Command;
import com.google.refine.model.AbstractOperation;
import com.google.refine.model.Project;
import com.google.refine.freebase.operations.ImportQADataOperation;
import com.google.refine.process.Process;

public class ImportQADataCommand extends Command {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ProjectManager.singleton.setBusy(true);
        try {
            Project project = getProject(request);
            
            AbstractOperation op = new ImportQADataOperation();
            Process process = op.createProcess(project, new Properties());
            
            performProcessAndRespond(request, response, project, process);
        } catch (Exception e) {
            respondException(response, e);
        } finally {
            ProjectManager.singleton.setBusy(false);
        }
    }
}
