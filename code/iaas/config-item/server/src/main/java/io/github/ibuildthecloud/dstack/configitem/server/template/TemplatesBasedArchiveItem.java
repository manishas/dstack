package io.github.ibuildthecloud.dstack.configitem.server.template;

import io.github.ibuildthecloud.dstack.configitem.server.model.impl.AbstractArchiveBasedConfigItem;
import io.github.ibuildthecloud.dstack.configitem.server.model.impl.ArchiveContext;
import io.github.ibuildthecloud.dstack.configitem.server.resource.Resource;
import io.github.ibuildthecloud.dstack.configitem.server.resource.ResourceRoot;
import io.github.ibuildthecloud.dstack.configitem.version.ConfigItemStatusManager;

import java.io.IOException;
import java.io.OutputStream;

public class TemplatesBasedArchiveItem extends AbstractArchiveBasedConfigItem {

    private static final String TEMPLATE_KEY = "template";

    TemplateFactory templateFactory;

    public TemplatesBasedArchiveItem(String name, ConfigItemStatusManager versionManager, ResourceRoot resourceRoot,
            TemplateFactory templateFactory) {
        super(name, versionManager, resourceRoot);
        this.templateFactory = templateFactory;
    }

    @Override
    protected void writeContent(final ArchiveContext context) throws IOException {
        super.writeContent(context);

        populateContext(context);

        for ( Resource resource : getResourceRoot().getResources() ) {
            Template template = null;
            Object cached = resource.getAttibute(TEMPLATE_KEY);

            if ( cached == null || ! ( cached instanceof Template )) {
                template = templateFactory.loadTemplate(resource);
                resource.setAttribute(TEMPLATE_KEY, template);
            } else {
                template = (Template)cached;
            }

            if ( template == null )
                continue;

            final Template templateFinal = template;
            withEntry(context, templateFinal.getOutputName(), template.getSize(), new WithEntry() {
                @Override
                public void with(OutputStream os) throws IOException {
                    templateFinal.execute(context.getData(), os);
                }
            });
        }
    }

    private void populateContext(ArchiveContext context) {
    }

}
