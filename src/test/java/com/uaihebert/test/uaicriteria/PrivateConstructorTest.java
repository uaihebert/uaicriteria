/*
 * Copyright 2013 uaiHebert Solucoes em Informatica
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * */
package com.uaihebert.test.uaicriteria;

import com.uaihebert.cto.UaiCriteriaBuilder;
import com.uaihebert.model.EntityPathHelper;
import com.uaihebert.uaicriteria.UaiCriteriaFactory;
import com.uaihebert.uaicriteria.base.element.BaseCriteriaFactory;
import com.uaihebert.uaicriteria.base.element.BasicCriteriaElementsFactory;
import com.uaihebert.uaicriteria.base.element.EasyCriteriaFactory;
import com.uaihebert.uaicriteria.path.AbstractPathCreator;
import com.uaihebert.uaicriteria.path.PathExtractor;
import com.uaihebert.uaicriteria.path.PathHelper;
import com.uaihebert.uaicriteria.path.RegularQueryPathCreator;
import com.uaihebert.uaicriteria.path.StringPathBreaker;
import com.uaihebert.uaicriteria.path.TupleQueryPathCreator;
import com.uaihebert.uaicriteria.predicate.AbstractPredicateCreator;
import com.uaihebert.uaicriteria.predicate.RegularQueryPredicateCreator;
import com.uaihebert.uaicriteria.predicate.TupleQueryPredicateCreator;
import com.uaihebert.util.ReflectionUtil;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static org.junit.Assert.assertNotNull;

public class PrivateConstructorTest {

    @Test
    public void isBasicCriteriaElementFactoryConstructorWorking() throws Exception {
        final Constructor<BasicCriteriaElementsFactory> constructor = BasicCriteriaElementsFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final BasicCriteriaElementsFactory object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isReflectionUtilConstructorWorking() throws Exception {
        final Constructor<ReflectionUtil> constructor = ReflectionUtil.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final ReflectionUtil object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isTupleQueryPathCreatorConstructorWorking() throws Exception {
        final Constructor<TupleQueryPathCreator> constructor = TupleQueryPathCreator.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final TupleQueryPathCreator object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isTupleQueryPredicateCreatorConstructorWorking() throws Exception {
        final Constructor<TupleQueryPredicateCreator> constructor = TupleQueryPredicateCreator.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final TupleQueryPredicateCreator object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isPathExtractorConstructorWorking() throws Exception {
        final Constructor<PathExtractor> constructor = PathExtractor.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final PathExtractor object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isEasyCriteriaFactoryConstructorWorking() throws Exception {
        final Constructor<EasyCriteriaFactory> constructor = EasyCriteriaFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final EasyCriteriaFactory object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isBaseCriteriaFactoryConstructorWorking() throws Exception {
        final Constructor<BaseCriteriaFactory> constructor = BaseCriteriaFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final BaseCriteriaFactory object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isUaiCriteriaFactoryConstructorWorking() throws Exception {
        final Constructor<UaiCriteriaFactory> constructor = UaiCriteriaFactory.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final UaiCriteriaFactory object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isEntityPathHelperConstructorWorking() throws Exception {
        final Constructor<EntityPathHelper> constructor = EntityPathHelper.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final EntityPathHelper object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isAbstractPredicateCreatorConstructorWorking() throws Exception {
        final Constructor<AbstractPredicateCreator> constructor = AbstractPredicateCreator.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final AbstractPredicateCreator object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isStringPathBreakerConstructorWorking() throws Exception {
        final Constructor<StringPathBreaker> constructor = StringPathBreaker.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final StringPathBreaker object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isAbstractPathCreatorConstructorWorking() throws Exception {
        final Constructor<AbstractPathCreator> constructor = AbstractPathCreator.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final AbstractPathCreator object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isPathHelperConstructorWorking() throws Exception {
        final Constructor<PathHelper> constructor = PathHelper.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final PathHelper object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isRegularQueryPredicateCreatorConstructorWorking() throws Exception {
        final Constructor<RegularQueryPredicateCreator> constructor = RegularQueryPredicateCreator.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final RegularQueryPredicateCreator object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isRegularQueryPathCreatorConstructorWorking() throws Exception {
        final Constructor<RegularQueryPathCreator> constructor = RegularQueryPathCreator.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final RegularQueryPathCreator object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }

    @Test
    public void isUaiCriteriaBuilderConstructorWorking() throws Exception {
        final Constructor<UaiCriteriaBuilder> constructor = UaiCriteriaBuilder.class.getDeclaredConstructor(new Class[0]);
        constructor.setAccessible(true);
        final UaiCriteriaBuilder object = constructor.newInstance(new Object[0]);
        assertNotNull(object);
    }
}