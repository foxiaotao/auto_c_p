package dhh.service.generator.codegen.elements;

import static org.mybatis.generator.internal.util.messages.Messages.getString;

import java.util.Set;
import java.util.TreeSet;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.javamapper.elements.AbstractJavaMapperMethodGenerator;

public class FindByPageMethodGenerator extends
AbstractJavaMapperMethodGenerator{
	@Override
    public void addInterfaceElements(Interface interfaze) {
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewMapInstance();
        FullyQualifiedJavaType listType;
        if (introspectedTable.getRules().generateBaseRecordClass()) {
            listType = new FullyQualifiedJavaType(introspectedTable
                    .getBaseRecordType());
        } else if (introspectedTable.getRules().generatePrimaryKeyClass()) {
            listType = new FullyQualifiedJavaType(introspectedTable
                    .getPrimaryKeyType());
        } else {
            throw new RuntimeException(getString("RuntimeError.12")); //$NON-NLS-1$
        }

        //importedTypes.add(listType);
        returnType.addTypeArgument(new FullyQualifiedJavaType("String,Object"));
        method.setReturnType(returnType);
        importedTypes.add(returnType);
        
        method.setName("findByPage");
        method.addException(new FullyQualifiedJavaType("Exception"));
        
//        FullyQualifiedJavaType exampleType = new FullyQualifiedJavaType(
//                introspectedTable.getExampleType());
//        method.addParameter(new Parameter(exampleType,
//                "example")); //$NON-NLS-1$ //$NON-NLS-2$
        importedTypes.add(listType);

        method.addParameter(new Parameter(listType,
                "record"));
        
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(),
                "startPage")); //$NON-NLS-1$ //$NON-NLS-2$
        
        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(),
                "endPage")); //$NON-NLS-1$ //$NON-NLS-2$
        
        importedTypes.add(FullyQualifiedJavaType.getNewIntegerInstance());
        

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);

        addMapperAnnotations(interfaze, method);
        
        if (context.getPlugins()
                .clientUpdateByExampleSelectiveMethodGenerated(method, interfaze,
                        introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

    public void addMapperAnnotations(Interface interfaze, Method method) {
        return;
    }

}
