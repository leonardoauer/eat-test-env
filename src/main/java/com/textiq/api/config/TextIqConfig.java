//package com.textiq.api.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.core.env.Environment;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//import com.google.inject.Injector;
//import com.textiq.api.mongo.service.EatMongoEmailChainService;
//import com.textiq.api.mongo.service.EatMongoMailService;
//import com.textiq.api.mongo.service.EatMongoPersonEntityService;
//import com.textiq.api.mongo.service.EatMongoSetService;
//import com.textiq.core.logging.TextIQLogger;
//import com.textiq.core.models.services.AnnotationService;
//import com.textiq.core.models.services.AttachmentService;
//import com.textiq.core.models.services.DocumentContentService;
//import com.textiq.core.models.services.ElasticsearchConfig;
//import com.textiq.core.models.services.EmbryoEntityService;
//import com.textiq.core.modules.CoreModule;
//import com.textiq.core.modules.SearchModule;
//import com.textiq.core.utilities.Bootstrap;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = {"com.textiq.core.models.repository"})
//@Import(value = {ElasticsearchConfig.class})
//public class TextIqConfig {
//    private Injector injector;
//
//    @Autowired
//    public TextIqConfig(Environment environment) throws Exception {
//        Bootstrap.init(new String[]{"config/textip-config.properties"});
//        injector = Bootstrap.injectModulesAndStartService(new CoreModule(), new SearchModule());
//    }
//
//    @Bean(name = "textIQLogger")
//    public TextIQLogger textIQLogger() {
//        return injector.getInstance(TextIQLogger.class);
//    }
//
//    @Bean(name = "eatMongoMailService")
//    public EatMongoMailService eatMongoMailService() {
//        return injector.getInstance(EatMongoMailService.class);
//    }
//    
//    @Bean(name = "eatMongoSetService")
//    public EatMongoSetService eatMongoSetService() {
//    	return injector.getInstance(EatMongoSetService.class);
//    }
//    
//    @Bean(name = "eatMongoPersonEntityService")
//    public EatMongoPersonEntityService eatMongoPersonEntityService() {
//    	return injector.getInstance(EatMongoPersonEntityService.class);
//    }
//    
//    @Bean(name = "eatMongoEmailChainService")
//    public EatMongoEmailChainService eatMongoEmailChainService() {
//    	return injector.getInstance(EatMongoEmailChainService.class);
//    }
//    
//    @Bean(name = "embryoEntityService")
//    public EmbryoEntityService embryoEntityService() {
//        return injector.getInstance(EmbryoEntityService.class);
//    }
//    
//    @Bean(name = "attachmentService")
//    public AttachmentService attachmentService() {
//        return injector.getInstance(AttachmentService.class);
//    }
//    
//    @Bean(name = "annotationService")
//    public AnnotationService annotationService() {
//        return injector.getInstance(AnnotationService.class);
//    }
//    
//    @Bean(name = "documentContentService")
//    public DocumentContentService documentContentService() {
//    	return injector.getInstance(DocumentContentService.class);
//    }
//}
